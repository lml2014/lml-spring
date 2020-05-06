package com.lml.part.spring.service.impl;

import com.lml.part.spring.domain.User;
import com.lml.part.spring.mapper.UserMapper;
import com.lml.part.spring.service.IHandUserService;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author shuishan
 * @date 2020/5/6
 */
@Service
public class HandUserService implements IHandUserService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Resource
    private UserMapper userMapper;

    @Override
    public void update(User user) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(definition);

        try {
            Assert.notNull(user.getId(), "user id not null!");
            Assert.notNull(user.getName(), "user name not null!");
            User u = userMapper.selectById(user.getId());
            Assert.notNull(u, "can't find user by id.");
            userMapper.update(user);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            logger.error("update user error, message:" + e.getMessage(), e);
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }
}
