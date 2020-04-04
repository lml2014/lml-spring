package com.lml.part.spring.service;

import com.lml.part.spring.domain.User;
import com.lml.part.spring.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuishan
 * @date 2020/4/4
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

}
