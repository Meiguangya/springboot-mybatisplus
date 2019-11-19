package com.example.springboot.dao.es;

import com.example.springboot.entity.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogDao extends ElasticsearchRepository<EsBlog, String> {




}
