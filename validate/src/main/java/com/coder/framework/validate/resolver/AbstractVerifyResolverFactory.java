package com.coder.framework.validate.resolver;

import com.coder.framework.validate.handle.AbstractVerifyAdapter;
import com.coder.framework.validate.support.AbstractVerifyRegistrySupport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
 * Build File @date: 2019/9/10 19:09
 * @version 1.0
 * @description
 */
class AbstractVerifyResolverFactory implements AbstractVerifyRegistrySupport {

    void createVerifyResolverFactory(JoinPoint joinPoint) {
        Method targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Field[] fields = arg.getClass().getDeclaredFields();
            if (fields.length == 0) {
                resolverHandle(targetMethod, arg);
                continue;
            }
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                resolverHandle(targetMethod, arg, field);
                field.setAccessible(accessible);
            }
        }
    }

    private void resolverHandle(Method targetMethod, Object arg) {
        resolverHandle(targetMethod, arg, null);
    }

    private void resolverHandle(Method targetMethod, Object arg, Field field) {
        for (AbstractVerifyAdapter abstractVerifyAdapter : getVerifyProcessProxyInstance()) {
            if (abstractVerifyAdapter.methodFilter(targetMethod, arg, field)) {
                abstractVerifyAdapter.coreProcessingMethod(arg, field);
            }
        }
    }

}
