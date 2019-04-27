package cn.edu.zucc.service.impl;
//import com.coding.studiodemo.User.UserService;
//import com.coding.studiodemo.User.User;
//import com.coding.studiodemo.User.UserService;
import cn.edu.zucc.domain.dao.UserRepository;
import cn.edu.zucc.domain.entity.User;
import cn.edu.zucc.service.UserService;
//import com.coding.studiodemo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @CachePut(value = "user",key = "#user.id")
    public void create(User user){
        userRepository.save(user);
    }
    @Override
    @CachePut(value = "user",key = "#user.id")
    public void update(User user){
        userRepository.save(user);
    }
    @Override
    @CacheEvict(value = "user",key = "#id")
    public void deleteById(long id){
        userRepository.deleteById(id);
    }
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public User getUser(long id){
        return userRepository.findById(id).get();
    }
    @Override
    public User getUser(String name){
        return userRepository.findByName(name);
    }
    @Override
    public User getUser(String name,String password){

        return userRepository.findByNameAndPassword(name,password);
    }


//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Override
//    public void create(String name, Integer age){
//        jdbcTemplate.update("insert into USER(NAME,AGE) values(?,?)",name,age);
//    }
//    @Override
//    public void update(long id,String name,Integer age){
//        jdbcTemplate.update("update USER set NAME=?,AGE=? where id=?",name,age,id);
//    }
//    @Override
//    public void deleteById(long id){
//        jdbcTemplate.update("delete from USER where id=?",id);
//    }
//    @Override
//    public List<Map<String,Object>> getAllUsers(){
//        return jdbcTemplate.queryForList("select * from USER");
//    }
//    @Override
//    public User getUser(long id){
//        return (User)jdbcTemplate.queryForObject("select * from USER where id=?",new Object[] {id},
//
//     new RowMapper<Object>(){
//         @Override
//            public Object mapRow(ResultSet rs, int rowMum)
//                    throws SQLException{
//                User emp=new User();
//                emp.setId(rs.getLong("id"));
//                emp.setName(rs.getString("name"));
//                emp.setAge(rs.getInt("age"));
//                return emp;
//            }
//        });
//
//    }

//    @Override
//    public List<Map<String, Object>> getAllUsersByLike(String pattern) {
//        return jdbcTemplate.queryForList("select * from USER where name like '%"+pattern+"%'");
//    }

//    @Override
//    public void deleteAllUsers(){
//        jdbcTemplate.update("delete from USER");
//    }
}

