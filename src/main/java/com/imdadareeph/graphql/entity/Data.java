package com.imdadareeph.graphql.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Data {

    @Id
    private int id;
    private String name;
    private String mobile;
    private String email;
    private String[] address;
}
