package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.example.springboot.dao.es.EsBlogDao;
import com.example.springboot.entity.Blog;
import com.example.springboot.entity.es.EsBlog;
import com.example.springboot.mapper.BlogMapper;
import com.example.springboot.utils.HttpBaseUtil;
import com.example.springboot.utils.HttpUtil;
import com.github.kevinsawicki.http.HttpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    public void addBlog() {
        Blog b = new Blog();
        b.setTitle("测试elasticsearch标题");
        b.setAuthor("lucy");
        b.setContent("这是一篇关于elasticsearch的短文,不好看不收钱");
        blogMapper.insert(b);
    }

    @Test
    public void testHttp() {
        String url = "localhost:8787/query?index=by_cs_index&q={\"multi_match\":[{\"query\":\"广东省广州市白云区滘心沙头大巷4号\",\"fields\":[\"stree_name\",\"address\"]}]}&page=1&size=10";

    }

    public static void main(String[] args) {
//        System.out.println(testHttpUtil());
        System.out.println(testBaseHttpUtil());
    }

    public static String testHttpUtil() {
        String url = "http://localhost:8787/index?index=sbss_doorplate_view";
        Map<String,Object> map = new HashMap<>();
        map.put("testName","好");
        String res = HttpUtil.request(url, map, null, HttpMethod.POST, MediaType.APPLICATION_JSON);
        return res;
    }

    public static String testBaseHttpUtil() {
        String url = "http://localhost:8787/index?index=sbss_doorplate_view";
        Map<String,Object> map = new HashMap<>();
        map.put("testName","啊啊");
        String res = HttpBaseUtil.request(url, JSON.toJSONString(map), null, "POST", "appliction/json");
        return res;
    }

}
