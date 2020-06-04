package com.lml.part.spring.business.user;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shuishan
 * @date 2020/6/3
 */
@Component
public class NameLengthTwo extends NameLengthSingle {

    @Override
    @Transactional
    public String[] splitByName(String name) {
        return super.splitName(name, "  ");
    }

}
