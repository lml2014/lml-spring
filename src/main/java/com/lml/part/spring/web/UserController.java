package com.lml.part.spring.web;

import com.lml.part.spring.domain.User;
import com.lml.part.spring.service.IHandUserService;
import com.lml.part.spring.service.IUserService;
import com.lml.part.spring.service.impl.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shuishan
 * @date 2020/4/4
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private IHandUserService handUserService;

    @RequestMapping("/insert")
    @ResponseBody
    public Object insert(String name) {
        Assert.hasText(name, "name not empty!");
        return userService.insert(name);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(Long id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        userService.update(user);
        return "success";
    }

    @RequestMapping("/update/hand")
    @ResponseBody
    public Object handUpdate(@RequestBody User user) {
        handUserService.update(user);
        return "success";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        Assert.notNull(id, "id not null!");
        return userService.delete(id);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectAll() {
        return userService.selectAll();
    }

    @RequestMapping("/insertSlowUser")
    @ResponseBody
    public Object insertSlowUser(String name) {
        Assert.hasText(name, "name not empty!");
        return userService.insertSlowUser(name);
    }

    @RequestMapping("/insertSlowUsers")
    @ResponseBody
    public Object insertSlowUsers(String names) {
        Assert.hasText(names, "name not empty!");
        return userService.insertSlowUsers(Arrays.stream(names.split(",")).collect(Collectors.toList()));
    }

}
