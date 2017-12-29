package com.zzw.repository;

import com.zzw.entity.ContentEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ContentRepository extends ElasticsearchRepository<ContentEntity,Integer> {
}
