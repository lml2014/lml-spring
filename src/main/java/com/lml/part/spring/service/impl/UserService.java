package com.lml.part.spring.service.impl;

import com.lml.part.spring.business.user.NameLengthSingle;
import com.lml.part.spring.business.user.NameLengthTwo;
import com.lml.part.spring.domain.MysqlLock;
import com.lml.part.spring.domain.User;
import com.lml.part.spring.mapper.UserMapper;
import com.lml.part.spring.service.IMysqlLockService;
import com.lml.part.spring.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shuishan
 * @date 2020/4/4
 */
@Service
public class UserService implements IUserService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;
    @Resource
    private IMysqlLockService mysqlLockService;
    @Resource
    private NameLengthSingle nameLengthSingle;
    @Resource
    private NameLengthTwo nameLengthTwo;

    private MysqlLock buildLock(String name) {
        return new MysqlLock("user_" + name);
    }

    @Override
    public int insert(String name) {
        return userMapper.insert(name);
    }

    @Override
    @Transactional
//    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        Assert.notNull(user, "user not null!");
        Assert.notNull(user.getId(), "user id not null!");
        Assert.notNull(user.getName(), "user name not null!");
        User u = userMapper.selectById(user.getId());
        Assert.notNull(u, "can't find user by id.");
        userMapper.update(user);
    }


    @Override
    public int delete(Long id) {
        return userMapper.delete(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    @Transactional
    public int insertSlowUser(String name) {
        logger.debug("thread[" + Thread.currentThread() + "] start slow insert user.");
        long start = System.currentTimeMillis();

        int result = mysqlLockService.lock(buildLock(name), () -> {
            int id = userMapper.insert(name);
            try {
                logger.debug("thread[" + Thread.currentThread() + "] sleep, id: " + id);
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                logger.error("thread[" + Thread.currentThread() + "] interrupted, message:" + e.getMessage(), e);
            }

            return id;
        });
        logger.debug("thread[" + Thread.currentThread() + "] slow insert success, took:" + (System.currentTimeMillis() - start));
        return result;
    }

    @Override
    @Transactional
    public List<Integer> insertSlowUsers(List<String> names) {
        logger.debug("thread[" + Thread.currentThread() + "] start slow insert users, names:" + names);
        long start = System.currentTimeMillis();

        List<MysqlLock> locks = names.stream().map(this::buildLock).collect(Collectors.toList());
        List<Integer> result = mysqlLockService.locks(locks, () -> {
            List<Integer> ids = names.stream().map(name -> userMapper.insert(name)).collect(Collectors.toList());
            try {
                logger.debug("thread[" + Thread.currentThread() + "] sleep, ids: " + ids);
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                logger.error("thread[" + Thread.currentThread() + "] interrupted, message:" + e.getMessage(), e);
            }

            return ids;
        });
        logger.debug("thread[" + Thread.currentThread() + "] slow insert success, took:" + (System.currentTimeMillis() - start));
        return result;
    }

    @Override
    public List<String> splitNames(Integer type, String[] names) {
        if (type == null || type == 0) {
            return nameLengthSingle.splitName(names);
        }
        return nameLengthTwo.splitName(names);
    }

}
