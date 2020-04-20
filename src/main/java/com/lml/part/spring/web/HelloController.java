package com.lml.part.spring.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shuishan
 * @date 2020/4/4
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/say")
    @ResponseBody
    public Object say(String params) {
        logger.trace("request URL:/hello/say, params:" + params);
        return "say hello";
    }

}
