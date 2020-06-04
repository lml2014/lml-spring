package com.lml.part.spring.business.user;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shuishan
 * @date 2020/6/3
 */
@Component
public class NameLengthSingle extends AbstractNameSplit implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private NameLengthSingle beanProxy;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void startup() {
        beanProxy = (NameLengthSingle) this.applicationContext.getBean(Introspector.decapitalize(this.getClass().getSimpleName()));
        System.out.println("class:" + this.getClass().getName() + ";initBean:" + beanProxy.getClass().getName());
    }

    public List<String> splitName(String[] names) {
        System.out.println("beanProxy:" + beanProxy.getClass().getName());
        List<String> result = new ArrayList<>();
        for (String name : names) {
            try {
                String[] n = beanProxy.splitByName(name);
                result.add("split success:" + JSON.toJSONString(n));
            } catch (Exception e) {
                result.add("error!");
            }
        }
        return result;
    }

    @Transactional
    public String[] splitByName(String name) {
        return super.splitName(name, " ");
    }

}
