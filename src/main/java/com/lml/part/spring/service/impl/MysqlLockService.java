package com.lml.part.spring.service.impl;

import com.google.common.collect.Lists;
import com.lml.part.spring.domain.MysqlLock;
import com.lml.part.spring.mapper.MysqlLockMapper;
import com.lml.part.spring.service.IMysqlLockService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author shuishan
 * @date 2020/4/20
 */
@Service
public class MysqlLockService implements IMysqlLockService {

    private Logger logger = Logger.getLogger(this.getClass());

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

    @Override
    @Transactional
    public <T> T lock(MysqlLock lock, Supplier<T> callback) {
        try {
            int row = mysqlLockMapper.insert(lock);
            logger.debug("thread[" + Thread.currentThread() + "] lock insert success, lock:" + lock.getKey() + ", row:" + row);
        } catch (Exception e) {
            logger.error("thread[" + Thread.currentThread() + "] lock[" + lock.getKey() + "] insert error," + e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

        T result = callback.get();

        try {
            mysqlLockMapper.delete(lock);
            logger.debug("thread[" + Thread.currentThread() + "] lock delete success, lock:" + lock.getKey());
        } catch (Exception e) {
            logger.error("thread[" + Thread.currentThread() + "] lock[" + lock.getKey() + "]  delete error," + e.getMessage(), e);
        }
        return result;
    }


}
