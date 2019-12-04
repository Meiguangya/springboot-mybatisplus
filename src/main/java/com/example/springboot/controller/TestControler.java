package com.example.springboot.controller;

import com.example.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestControler {


    @PostMapping("/t1")
    @ResponseBody
    public Object t1(@RequestBody User u) {
        return u;
    }

    @PostMapping("/t2")
    @ResponseBody
    public String t1(String name, String age) {
        System.out.println("哈哈");
        return name + age;
    }
}
