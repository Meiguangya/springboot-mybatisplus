package com.example.springboot.service;

import com.example.springboot.dao.es.EsBlogDao;
import com.example.springboot.entity.es.EsBlog;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsBlogServiceImpl implements IEsBlogService {

    @Autowired
    public EsBlogDao esBlogDao;

    @Override
    public List<EsBlog> qryEsBlog(String keyword) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.should(QueryBuilders.matchPhraseQuery("title",keyword));
        builder.should(QueryBuilders.matchPhraseQuery("content",keyword));
        System.out.println(builder.toString());

        Page<EsBlog> esBlogPage = (Page<EsBlog>)esBlogDao.search(builder);
        return esBlogPage.getContent();
    }
}
