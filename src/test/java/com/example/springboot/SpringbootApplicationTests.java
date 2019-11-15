package com.example.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}

	@Test
	public void testInsert(){
		User u = new User();
		u.setName("蔡徐坤22");
		u.setAge(10);
		u.setEmail("1@qq.com");
		u.setRemark("123213");
		int row = userMapper.insert(u);
		System.out.println(row);
	}

	@Test
	public void test1(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.likeRight("name","蔡").and(e->e.lt("age",20).or().eq("email","2@qq.com"));
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	//age<20 或者邮箱=1@qq.com 并且姓蔡
	@Test
	public void test2(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.nested(q->q.lt("age",20).or().eq("email","1@qq.com"))
				.likeRight("name","蔡");
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	//age<20 或者邮箱=1@qq.com 并且姓蔡
	@Test
	public void test3(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.in("age", Arrays.asList(10,30,18,20));
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	//age<20 或者邮箱=1@qq.com 并且姓蔡
	@Test
	public void test4(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.select("name","age","id").in("age", Arrays.asList(10,30,18,20));
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	//age<20 或者邮箱=1@qq.com 并且姓蔡
	@Test
	public void test5(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.select(User.class,t->{return !t.getColumn().equals("id") && !t.getColumn().equals("age");});
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

}
