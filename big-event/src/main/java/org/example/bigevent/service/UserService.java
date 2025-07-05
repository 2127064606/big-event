package org.example.bigevent.service;

import org.example.bigevent.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService {
    public User getUserByUsername(String username);

    public User register(User user);

    public String login(String username, String password);

    public void updateUser(User user);

    public User getUserById(Integer id);

    public void updatePassword(Map<String, String>map);

    public String addUserPic(MultipartFile file);
}
