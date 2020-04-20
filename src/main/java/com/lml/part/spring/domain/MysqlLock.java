package com.lml.part.spring.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author shuishan
 * @date 2020/4/20
 */
public class MysqlLock implements Serializable {

    private String key;

    private Date created;

    private Integer total;

    public MysqlLock() {
    }

    public MysqlLock(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
