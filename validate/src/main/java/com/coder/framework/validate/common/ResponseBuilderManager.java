package com.coder.framework.validate.common;

import com.coder.framework.validate.config.VerifyInterpreterRegistry;
import com.coder.framework.validate.exception.VerifyFrameworkInitializeException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
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
 * Build File @date: 2019/9/9 14:14
 * @version 1.0
 * @description
 */
public class ResponseBuilderManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void initResponseBuilder() {
        VerifyInterpreterRegistry registry = getVerifyInterpreterRegistryBean();
        for (Map.Entry<String, Object> entry : registry.getValidResponseEntityField().entrySet()) {
            try {
                Class<?> responseEntity = registry.getResponseEntity();
                Field field = responseEntity.getDeclaredField(entry.getValue().toString());
                responseEntity.getDeclaredMethod(generatorSetMethodName(entry.getValue().toString()), field.getType());
                try {
                    responseEntity.getDeclaredMethod(generatorGetMethodName(entry.getValue().toString()));
                } catch (NoSuchMethodException e) {
                    try {
                        responseEntity.getDeclaredMethod(generatorBooleanMethodName(entry.getValue().toString()));
                    } catch (NoSuchMethodException ex) {
                        throw new VerifyFrameworkInitializeException("The response class does not provide a get or set method for a [" + entry.getValue() + "] field.");
                    }
                }
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                throw new VerifyFrameworkInitializeException("The response class does not provide a get or set method for a [" + entry.getValue() + "] field.");
            }
        }
    }

    public ResponseBuilder builder() {
        return new ResponseBuilder(getVerifyInterpreterRegistryBean().getResponseEntity(),getVerifyInterpreterRegistryBean());
    }

    private VerifyInterpreterRegistry getVerifyInterpreterRegistryBean() {
        return applicationContext.getBean(VerifyInterpreterRegistry.class);
    }

    private String generatorGetMethodName(String fieldName) {
        return "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
    }

    private String generatorBooleanMethodName(String fieldName) {
        return "is" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
    }

    private String generatorSetMethodName(String fieldName) {
        return "set" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
    }

}
