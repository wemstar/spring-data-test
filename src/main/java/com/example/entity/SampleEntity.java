package com.example.entity;

import javax.persistence.*;

/**
 * Created by wemstar on 2016-03-03.
 */
@Entity
@Table(name = "SAMPLE_ENTITY")
public class SampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SAMPLE_ENTITY_ID")
    private Long id;
    @Column(name = "SAMPLE_ENTITY_EXTERNAL_ID",nullable = false,unique = true)
    private Long externalId;
    @Column(name = "SAMPLE_ENTITY_FIELD1")
    private String field1;
    @Column(name = "SAMPLE_ENTITY_FIELD2")
    private String filed2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getFiled2() {
        return filed2;
    }

    public void setFiled2(String filed2) {
        this.filed2 = filed2;
    }
}
