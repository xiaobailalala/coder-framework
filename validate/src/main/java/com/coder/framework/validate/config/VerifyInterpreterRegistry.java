package com.coder.framework.validate.config;


import com.coder.framework.validate.aspect.VerifyMethodArgumentResolver;
import com.coder.framework.validate.handle.GlobalExceptionHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author zpx
 * Build File @date: 2019/9/5 15:12
 * @version 1.0
 * @description
 */
public class VerifyInterpreterRegistry implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public VerifyMethodArgumentResolver verifyMethodArgumentResolver() {
        return new VerifyMethodArgumentResolver();
    }

    @Bean
    public GlobalExceptionHandle globalExceptionHandle() {
        return new GlobalExceptionHandle();
    }



    @Override
    public void run(String... args) {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(SpringBootApplication.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getClass().getName());
        }
    }
}
