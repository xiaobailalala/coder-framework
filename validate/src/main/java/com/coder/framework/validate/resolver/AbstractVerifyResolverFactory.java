package com.coder.framework.validate.resolver;

import com.coder.framework.validate.exception.VerifyFrameworkRegistryException;
import com.coder.framework.validate.adapter.AbstractVerifyAdapter;
import com.coder.framework.validate.adapter.AbstractVerifyResolverHandle;
import com.coder.framework.validate.support.AbstractVerifyRegistrySupport;
import com.coder.framework.validate.util.MethodParameter;
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
        for (int index = 0; index < args.length; index++) {
            Field[] fields = args[index].getClass().getDeclaredFields();
            if (fields.length == 0) {
                resolverHandle(new MethodParameter(targetMethod, index), args[index]);
                continue;
            }
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                resolverHandle(new MethodParameter(targetMethod, index), args[index], field);
                field.setAccessible(accessible);
            }
        }
    }

    private void resolverHandle(MethodParameter targetMethod, Object arg) {
        resolverHandle(targetMethod, arg, null);
    }

    @SuppressWarnings("all")
    private void resolverHandle(MethodParameter targetMethod, Object arg, Field field) {
        for (AbstractVerifyAdapter abstractVerifyAdapter : getVerifyProcessProxyInstance()) {
            if (abstractVerifyAdapter.methodFilter(targetMethod, arg, field)) {
                AbstractVerifyResolverHandle abstractVerifyResolverHandle = abstractVerifyAdapter.verifyHandleSupportFactory(targetMethod, arg, field);
                if (!(abstractVerifyResolverHandle instanceof AbstractVerifyResolverHandle)) {
                    throw new VerifyFrameworkRegistryException("Parsing objects [" + abstractVerifyAdapter + "] requires " +
                            "create the [" + AbstractVerifyResolverHandle.class + "] instance");
                }
                abstractVerifyAdapter.coreProcessingMethod(abstractVerifyResolverHandle);
            }
        }
    }

}
