package com.example.springboot.controller;

import com.example.springboot.entity.Blog;
import com.example.springboot.entity.es.EsBlog;
import com.example.springboot.service.IBlogService;
import com.example.springboot.service.IEsBlogService;
import com.example.springboot.vo.Params;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    IBlogService blogService;

    @Autowired
    IEsBlogService esBlogService;

    @GetMapping("/blogs")
    public Object qryBlog(){
        return blogService.qryAllBlog();
    }

    @PostMapping("/search")
    public Object search(@RequestBody Params params){
        Map<String,Object> map = new HashMap<String,Object>();

        StopWatch watch = new StopWatch();
        watch.start();

        if("mysql".equals(params.getType())){
            List<Blog> blogs = blogService.search(params.getKeyword());
            map.put("list",blogs);
        }

        if("es".equals(params.getType())){
            List<EsBlog> blogs = esBlogService.qryEsBlog(params.getKeyword());
            map.put("list",blogs);
        }

        watch.stop();
        map.put("duration",watch.getTotalTimeMillis());

        return map;
    }

}
