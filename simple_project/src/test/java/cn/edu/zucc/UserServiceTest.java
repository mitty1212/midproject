package cn.edu.zucc;

import cn.edu.zucc.domain.entity.User;
import cn.edu.zucc.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class UserServiceTest extends StudioDemoApplicationTests {
    @Autowired
    UserService userService;
    @Test
    public void test001create(){
        User user1 = new User();
        user1.setName("Tom");
        user1.setAge(20);
        User user2 = new User();
        user2.setName("Jerry");
        user2.setAge(20);
        userService.create(user1);
        userService.create(user2);
        Assert.assertThat(user1.getId(),is(1L));
        Assert.assertThat(user2.getId(),is(2L));
    }
    @Test
    public void test002update(){
        User user1 = new User();
        user1.setName("TomCat");
        user1.setAge(20);
        user1.setId(1L);

        userService.update(user1);
        User user = userService.getUser(1L);
        Assert.assertThat(user.getName(),is(user1.getName()));
    }
    @Test
    public void test003deleteById(){

//        User user1 = new User();
//        user1.setName("TomCat");
//        user1.setAge(20);
//        user1.setId(1L);
//        userService.create(user1);

        Assert.assertThat(userService.getAllUsers().size(),is(2));
        userService.deleteById(1L);
        Assert.assertThat(userService.getAllUsers().size(),is(1));

    }
    @Test
    public void test004getAllUsers(){
        User user1 = new User();
        user1.setName("TomCat");
        user1.setAge(20);
        user1.setId(1L);

        List<User> users = userService.getAllUsers();
        Assert.assertThat(users.get(0).getName(),is(user1.getName()));
    }
}
