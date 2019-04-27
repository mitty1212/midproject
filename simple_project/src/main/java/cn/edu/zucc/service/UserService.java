package cn.edu.zucc.service;

//import com.coding.studiodemo.User.User;

import cn.edu.zucc.domain.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
//    void create(String name, Integer age);
//    void update(long id, String name, Integer age);
//    void deleteById(long id);
//    List<Map<String,Object>> getAllUsers();
//    User getUser(long id);
//    void deleteAllUsers();
    //List<Map<String,Object>> getAllUsersByLike(String pattern);
    void create(User user);
    void update(User user);
    void deleteById(long id);
    List<User> getAllUsers();
    User getUser(long id);
    User getUser(String name);
    User getUser(String name,String password);
}
