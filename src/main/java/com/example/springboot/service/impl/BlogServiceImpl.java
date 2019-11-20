package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Blog;
import com.example.springboot.mapper.BlogMapper;
import com.example.springboot.service.IBlogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    public List<Blog> qryAllBlog() {
        return list(null);
    }

    @Override
    public List<Blog> search(String keyword) {
        return list(Wrappers.<Blog>lambdaQuery().like(Blog::getTitle, keyword).
                or().like(Blog::getContent, keyword));
    }
}
