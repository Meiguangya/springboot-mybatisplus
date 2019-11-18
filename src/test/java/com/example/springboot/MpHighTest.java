package com.example.springboot;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpHighTest {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void testLogicDel(){
        int rows = userMapper.deleteById("1094592041087729666");
        System.out.println(rows);
    }


    //测试@TableField(select = false)
    @Test
    public void testSelect(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectAll(){
        List<User> list = userMapper.getAll(Wrappers.<User>lambdaQuery());
        list.forEach(System.out::println);
    }

    @Test
    public void testInsetFill(){
        User u = new User("熊霸",11,"xb@qq.com");
        userMapper.insert(u);
    }

    @Test
    public void testUpdateFill(){
        User u = userMapper.selectById("1094590409767661570");
        u.setAge(19);
        userMapper.updateById(u);
    }


}
