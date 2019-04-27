package com.coding.studiodemo;

import cn.edu.zucc.domain.entity.User;
import cn.edu.zucc.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
