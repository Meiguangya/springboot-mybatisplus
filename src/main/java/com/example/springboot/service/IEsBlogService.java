package com.example.springboot.service;

import com.example.springboot.entity.es.EsBlog;

import java.util.List;

public interface IEsBlogService {

    public List<EsBlog> qryEsBlog(String keyword);

}
