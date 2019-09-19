package com.coder.framework.validate.handle;

import com.coder.framework.validate.annotation.VerifyNotNull;
import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.exception.VerifyBaseException;
import org.springframework.util.ObjectUtils;

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
 * Build File @date: 2019/9/10 18:36
 * @version 1.0
 * @description
 */
@VerifyOrder(1)
public class VerifyNotNullAdapterHandle implements AbstractVerifyAdapter, VerifyResolverHandle {

    @Override
    public boolean methodFilter(Method method, Object arg, Field field) {
//        (ObjectUtils.isEmpty(method) && !ObjectUtils.isEmpty(arg) && !ObjectUtils.isEmpty(arg.getClass().getDeclaredAnnotation(VerifyNotNull.class)))
        return !ObjectUtils.isEmpty(arg.getClass().getDeclaredAnnotation(VerifyNotNull.class)) ||
                !ObjectUtils.isEmpty(field.getClass().getDeclaredAnnotation(VerifyNotNull.class));
    }

    @Override
    public VerifyBaseException coreProcessingMethod(VerifyResolverHandle handle) {
        return null;
    }
}
