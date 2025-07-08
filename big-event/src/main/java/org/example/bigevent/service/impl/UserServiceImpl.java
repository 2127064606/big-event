package org.example.bigevent.service.impl;

import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.bigevent.exception.DuplicateDataException;
import org.example.bigevent.exception.ErrorFormatInfoException;
import org.example.bigevent.mapper.UserMapper;
import org.example.bigevent.pojo.User;
import org.example.bigevent.service.UserService;
import org.example.bigevent.utils.JwtGenerateUtil;
import org.example.bigevent.utils.OSSClientUtil;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User register(User user) {
        try {
            String usernamePattern = "^\\S{5,16}$";
            String passwordPattern = "^\\S{5,16}$";
            if(!user.getUsername().matches(usernamePattern)||!user.getPassword().matches(passwordPattern)){
                throw new ErrorFormatInfoException("用户名或密码格式错误");
            }
            User user1 = userMapper.selectByUsername(user.getUsername());
            if(user1 == null){
               user.setCreateTime(LocalDateTime.now());
               user.setUpdateTime(LocalDateTime.now());
                userMapper.insertUser(user);
                return user;
            }
            throw new DuplicateDataException("用户已存在");
        }catch (DuplicateDataException e){
            throw new DuplicateDataException(e.getMessage());
        }catch(ErrorFormatInfoException e){
            throw new ErrorFormatInfoException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String login(String username, String password) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        User user = userMapper.selectByUsername(username);
        if(user == null){
            throw new RuntimeException("用户名错误");
        }
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("密码错误");
        }
        Map<String, Object>maps = new HashMap<>();
        maps.put("id", user.getId());
        maps.put("username", user.getUsername());
        String token = JwtGenerateUtil.generateJwt("user", maps, Algorithm.HMAC256(JwtGenerateUtil.secretKey), 1);
        operations.set("token", token);
        return token;
    }

    @Override
    public void updateUser(User user) {
        String usernamePattern = "^\\S{5,16}$";
        String nicknamePattern = "^\\S{1,10}$";
        String emailPattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean usernamePatternCheck = user.getUsername() == null || user.getUsername().matches(usernamePattern);
        boolean nicknamePatternCheck = user.getNickname() == null || user.getNickname().matches(nicknamePattern);
        boolean emailPatternCheck = user.getEmail() == null || user.getEmail().matches(emailPattern);
       if(!usernamePatternCheck)throw new ErrorFormatInfoException("用户名格式错误");
       if(!nicknamePatternCheck)throw new ErrorFormatInfoException("昵称格式错误");
       if(!emailPatternCheck)throw new ErrorFormatInfoException("邮箱格式错误");
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updatePassword(Map<String, String> map) {
        String oldPsw = map.get("old_psw");
        String newPsw = map.get("new_psw");
        String rePsw = map.get("re_psw");
        int uid = Integer.parseInt(ThreadLocalUtil.get().get("id").toString());
        User user = userMapper.selectById(uid);
        boolean check = user == null || !user.getPassword().equals(oldPsw);
        log.info("check:{}",check);
        if(check)throw new RuntimeException("原密码错误");
        if(!newPsw.equals(rePsw))throw new RuntimeException("两次密码不一致");
        user.setPassword(newPsw);
        userMapper.update(user);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete("token");
    }

    @Override
    public String addUserPic(MultipartFile file) {
        String originalFilename = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
        String originUrl = "";
        try {
            originUrl = OSSClientUtil.uploadToOss(originalFilename, file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return originUrl;
    }

}
