package com.example.springboot;

import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    public IUserService userService;

    @Test
    public void testList() {
        List<User> list = userService.qryUserByName("雨");
        list.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        User u1 = new User("小黄蜂", 10, "xiaohuangfeng@qq.com");

        User u2 = new User("大黄蜂", 20, "dahuanfeng@qq.com");

        System.out.println(userService.saveUsers(Arrays.asList(u1, u2)));

    }

    @Test
    public void testUpdate() {
        List<User> userList = userService.qryUserByName("雨");
        User u1 = new User("大黄蜂",20,"dahuangfeng@177.com");
        u1.setId("dahuangfeng");
        User u2 = new User("xiao黄蜂",20,"xiaohuangfeng@177.com");
        u2.setId("xiaohuangfeng");
        System.out.println(userService.update(Arrays.asList(u1,u2)));
    }

    @Test
    public void testChain(){
        userService.chain();
    }

}
