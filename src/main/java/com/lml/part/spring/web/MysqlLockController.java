package com.lml.part.spring.web;

import com.lml.part.spring.domain.MysqlLock;
import com.lml.part.spring.service.IMysqlLockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/insert/batch", method = RequestMethod.POST)
    @ResponseBody
    public Object insertBatch(String keys) {
        List<MysqlLock> locks = Arrays.stream(keys.split(",")).map(MysqlLock::new).collect(Collectors.toList());
        return mysqlLockService.batchInsert(locks);
    }

    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteBatch(String keys) {
        List<MysqlLock> locks = Arrays.stream(keys.split(",")).map(MysqlLock::new).collect(Collectors.toList());
        return mysqlLockService.batchDelete(locks);
    }
}
