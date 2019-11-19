package com.example.springboot;

import com.example.springboot.dao.es.EsBlogDao;
import com.example.springboot.entity.Blog;
import com.example.springboot.entity.es.EsBlog;
import com.example.springboot.mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    public EsBlogDao esBlogDao;

    @Autowired
    public BlogMapper blogMapper;

    @Test
    public void test1() {
        Iterable<EsBlog> all = esBlogDao.findAll();
        Iterator<EsBlog> iterator = all.iterator();
        EsBlog b = iterator.next();
        System.out.println(b);

    }

    @Test
    public void addBlog(){
        Blog b = new Blog();
        b.setTitle("测试elasticsearch标题");
        b.setAuthor("lucy");
        b.setContent("这是一篇关于elasticsearch的短文,不好看不收钱");
        blogMapper.insert(b);
    }

}
