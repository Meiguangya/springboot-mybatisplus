package com.example.springboot.service;

import com.example.springboot.entity.Blog;

import java.util.List;

public interface IBlogService {

    public List<Blog> qryAllBlog();

    List<Blog> search(String keyword);
}
