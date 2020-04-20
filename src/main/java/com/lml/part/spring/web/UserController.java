package com.lml.part.spring.web;

import com.lml.part.spring.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author shuishan
 * @date 2020/4/4
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectAll() {
        return userService.selectAll();
    }

}
