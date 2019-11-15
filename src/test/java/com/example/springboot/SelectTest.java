package com.example.springboot;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SelectTest {

    @Autowired
    private UserMapper userMapper;

    //名字中包含雨并且年龄小于40
    @Test
    public void test1() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "雨").lt(User::getAge, 40);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(u -> System.out.println(u));
    }

    //名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
    @Test
    public void test2() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "雨").gt(User::getAge, 20)
                .lt(User::getAge, 40).isNotNull(User::getEmail);

        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    //名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
    @Test
    public void test3() {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "王").or().ge(User::getAge, 25).orderByAsc(User::getAge);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);

    }

    //创建日期为2019年2月14日并且直属上级为名字为王姓
    @Test
    public void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("date_format(create_time,'%Y-%m-%d')", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '%王%' ");

        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    //名字为王姓并且（年龄小于40或邮箱不为空）
    @Test
    public void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.likeRight("name", "王")
                .and(w -> w.lt("age", 40).or().isNotNull("email"));
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    //名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
    @Test
    public void test6() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.likeRight(User::getName,"王").or()
        .lt(User::getAge,40).gt(User::getAge,20).isNotNull(User::getEmail);

        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    //（年龄小于40或邮箱不为空）并且名字为王姓
    @Test
    public void test7() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.nested(w->w.lt(User::getAge,40).or().isNotNull(User::getEmail))
                .likeRight(User::getName,"王");

        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    //年龄为30、31、34、35
    //只返回满足条件的其中一条语句即可
    @Test
    public void test8() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.in(User::getAge, Arrays.asList(30,31,34,35)).last("limit 1");

        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test9() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getId,User::getName,User::getAge,User::getEmail)
                .like(User::getName, "雨").lt(User::getAge, 40);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(u -> System.out.println(u));
    }

    //11、按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
    //并且只取年龄总和小于500的组。
    @Test
    public void test10() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("avg(age),max(age),min(age)").groupBy("manager_id").having("sum(age)<{0}",500);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(u -> System.out.println(u));
    }



}
