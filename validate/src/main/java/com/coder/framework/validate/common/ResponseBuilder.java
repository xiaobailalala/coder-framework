package com.coder.framework.validate.common;

import com.coder.framework.validate.config.VerifyInterpreterRegistry;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.util.ObjectUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
 * Build File @date: 2019/9/9 17:24
 * @version 1.0
 * @description
 */
public class ResponseBuilder {

    private Object responseEntity;
    private VerifyInterpreterRegistry verifyInterpreterRegistry;

    ResponseBuilder(Class<?> responseEntity, VerifyInterpreterRegistry verifyInterpreterRegistry){
        try {
            this.responseEntity = responseEntity.newInstance();
            this.verifyInterpreterRegistry = verifyInterpreterRegistry;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ResponseBuilder messageBuilder(String message) {
        Object field = this.verifyInterpreterRegistry.getValidResponseEntityField().get(ResponseEntityMode.MESSAGE.getFieldName());
        if (!ObjectUtils.isEmpty(field)) {
            invokeSetMethod(field, message);
        }
        return this;
    }

    public ResponseBuilder statusBuilder(int status) {
        Object field = this.verifyInterpreterRegistry.getValidResponseEntityField().get(ResponseEntityMode.STATUS.getFieldName());
        if (!ObjectUtils.isEmpty(field)) {
            invokeSetMethod(field, status);
        }
        return this;
    }

    public ResponseBuilder conditionBuilder(boolean condition) {
        Object field = this.verifyInterpreterRegistry.getValidResponseEntityField().get(ResponseEntityMode.CONDITION.getFieldName());
        if (!ObjectUtils.isEmpty(field)) {
            invokeSetMethod(field, condition);
        }
        return this;
    }

    public ResponseBuilder dataBuilder(Object data) {
        Object field = this.verifyInterpreterRegistry.getValidResponseEntityField().get(ResponseEntityMode.DATA.getFieldName());
        if (!ObjectUtils.isEmpty(field)) {
            invokeSetMethod(field, data);
        }
        return this;
    }

    public Object generatorResponseEntity() {
        BeanGenerator beanGenerator = new BeanGenerator();
        for (Map.Entry<String, Object> entry : this.verifyInterpreterRegistry.getValidResponseEntityField().entrySet()) {
            try {
                Field declaredField = this.responseEntity.getClass().getDeclaredField(entry.getValue().toString());
                beanGenerator.addProperty(entry.getValue().toString(), declaredField.getType());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        Object responseObj = beanGenerator.create();
        BeanCopier copier = BeanCopier.create(this.responseEntity.getClass(), responseObj.getClass(), false);
        copier.copy(this.responseEntity, responseObj, null);
        return responseObj;
    }

    private <T> void invokeSetMethod(Object field, T value) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.toString(), this.responseEntity.getClass());
            Method setMethod = propertyDescriptor.getWriteMethod();
            if (setMethod != null) {
                setMethod.invoke(this.responseEntity, value);
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
