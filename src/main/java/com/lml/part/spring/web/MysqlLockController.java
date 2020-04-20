package com.lml.part.spring.web;

import com.lml.part.spring.domain.MysqlLock;
import com.lml.part.spring.service.IMysqlLockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author shuishan
 * @date 2020/4/20
 */
@Controller
@RequestMapping("/mysql/lock")
public class MysqlLockController {

    @Resource
    private IMysqlLockService mysqlLockService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Object insert(String key) {
        return mysqlLockService.insert(new MysqlLock(key));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(String key) {
        return mysqlLockService.delete(new MysqlLock(key));
    }
}
