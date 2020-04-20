package com.lml.part.spring.service.impl;

import com.lml.part.spring.domain.User;
import com.lml.part.spring.mapper.UserMapper;
import com.lml.part.spring.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuishan
 * @date 2020/4/4
 */
@Service
public class UserService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

}
