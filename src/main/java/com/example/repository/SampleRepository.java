package com.example.repository;

import com.example.entity.SampleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.Resource;


/**
 * Created by wemstar on 2016-03-03.
 */
@RepositoryRestResource(collectionResourceRel = "sample", path = "sample")
public interface SampleRepository extends PagingAndSortingRepository<SampleEntity,Long> {

    SampleEntity findByExternalId(@Param("externalId") Long externalId);
}
