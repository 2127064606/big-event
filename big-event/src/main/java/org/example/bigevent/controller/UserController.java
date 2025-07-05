package org.example.bigevent.controller;

import org.example.bigevent.pojo.Result;
import org.example.bigevent.pojo.User;
import org.example.bigevent.service.UserService;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Parser;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(User user){
        User user1 = userService.register(user);
        return Result.success(user1);
    }

    @PostMapping("/login")
    public Result login(String username, String password){
        String token = userService.login(username, password);
        return Result.success(token);
    }

    @GetMapping("/userInfo")
    public Result userInfo(){
        try{
            String username = ThreadLocalUtil.get().get("username").toString();
            User user = userService.getUserByUsername(username);
            return Result.success(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public Result update(@RequestBody User user){
        System.out.println(user);
        userService.updateUser(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @PatchMapping
    public Result updateAvatar(@URL String avatarUrl){
        User user = new User();
        user.setUserPic(avatarUrl);
        user.setId(Integer.parseInt(ThreadLocalUtil.get().get("id").toString()));
        userService.updateUser(user);
        return Result.success();
    }

    @PatchMapping("/psw")
    public Result updatePsw(@RequestBody Map<String, String>map){
        userService.updatePassword(map);
        return Result.success();
    }

}
