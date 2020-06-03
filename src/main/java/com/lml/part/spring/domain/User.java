package com.lml.part.spring.domain;

import java.io.Serializable;

/**
 * @author shuishan
 * @date 2020/4/4
 */
public class User implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

}
