package com.lml.part.spring.service.impl;

import com.google.common.collect.Lists;
import com.lml.part.spring.domain.MysqlLock;
import com.lml.part.spring.mapper.MysqlLockMapper;
import com.lml.part.spring.service.IMysqlLockService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuishan
 * @date 2020/4/20
 */
@Service
public class MysqlLockService implements IMysqlLockService {

    @Resource
    private MysqlLockMapper mysqlLockMapper;

    private void checkParams(List<MysqlLock> locks) {
        Assert.notEmpty(locks, "locks not null!");
        Assert.isTrue(locks.stream().allMatch(lock -> StringUtils.hasText(lock.getKey())), "key not null!");
    }

    @Override
    public int insert(MysqlLock lock) {
        checkParams(Lists.newArrayList(lock));
        return mysqlLockMapper.insert(lock);
    }

    @Override
    public int batchInsert(List<MysqlLock> locks) {
        checkParams(Lists.newArrayList(locks));
        return mysqlLockMapper.batchInsert(locks);
    }


    @Override
    public int delete(MysqlLock lock) {
        return mysqlLockMapper.delete(lock);
    }
}
