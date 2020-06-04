package com.lml.part.spring.business.user;

import org.springframework.util.Assert;

/**
 * @author shuishan
 * @date 2020/6/3
 */
public abstract class AbstractNameSplit {

    String[] splitName(String name, String regex) {
        Assert.hasText(name, "name not null!");
        Assert.notNull(regex, "split not null!");
        return name.split(regex);
    }

}
