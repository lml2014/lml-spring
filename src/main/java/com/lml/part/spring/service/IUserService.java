package com.lml.part.spring.service;

import com.lml.part.spring.domain.User;

import java.util.List;

/**
 * @author shuishan
 * @date 2020/4/20
 */
public interface IUserService {

    int insert(String name);

    int delete(Long id);

    List<User> selectAll();

    int insertSlowUser(String name);
}
