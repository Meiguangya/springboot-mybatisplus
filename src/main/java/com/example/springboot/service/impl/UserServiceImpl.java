package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> qryUserByName(String name) {
        List<User> list = list(Wrappers.<User>lambdaQuery().like(User::getName, name));
        return list;
    }


    @Override
    public boolean saveUsers(List<User> list) {
        boolean flag = false;
        try {
            flag = saveBatch(list);
            //写个异常
            int i = Integer.parseInt("ss");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("uuuuu");

        return flag;
    }

    @Override
    public boolean update(List<User> list){
        return saveOrUpdateBatch(list);
    }

    @Override
    public void chain(){
        //查询
        query().eq("name","大黄蜂").list().forEach(System.out::println);
        //修改
        lambdaUpdate().like(User::getName,"黄蜂").set(User::getEmail,"hh@qq.com").update();

        Map<String,Object> map = new HashMap<>();
        map.put("name","刘红雨");
        map.put("age",27);
        query().allEq(map).list().forEach(System.out::println);

    }



}
