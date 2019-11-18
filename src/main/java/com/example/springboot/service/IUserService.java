package com.example.springboot.service;

import com.example.springboot.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IUserService {

    List<User> qryUserByName(String name);

    boolean saveUsers(List<User> list);

    boolean update(List<User> list);

    void chain();
}
