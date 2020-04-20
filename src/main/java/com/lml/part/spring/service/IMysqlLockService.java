package com.lml.part.spring.service;

import com.lml.part.spring.domain.MysqlLock;

import java.util.List;

/**
 * @author shuishan
 * @date 2020/4/20
 */
public interface IMysqlLockService {

    int insert(MysqlLock lock);

    int batchInsert(List<MysqlLock> locks);

    int delete(MysqlLock lock);

}
