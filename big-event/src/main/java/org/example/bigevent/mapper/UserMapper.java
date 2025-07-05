package org.example.bigevent.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.bigevent.pojo.User;

@Mapper
public interface UserMapper {
    public User selectByUsername(String username);

    public void insertUser(User user);

    public void update(User user);

    public User selectById(Integer id);
}
