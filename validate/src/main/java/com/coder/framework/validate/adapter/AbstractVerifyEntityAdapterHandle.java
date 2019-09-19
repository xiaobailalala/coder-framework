package com.coder.framework.validate.adapter;

import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.exception.VerifyBaseException;

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
 * Build File @date: 2019/9/10 20:22
 * @version 1.0
 * @description
 */
@VerifyOrder(2)
public class AbstractVerifyEntityAdapterHandle implements AbstractVerifyAdapter {

    @Override
    public boolean methodFilter(Method method, Object arg, Field field) {
        return false;
    }

    @Override
    public VerifyBaseException coreProcessingMethod(AbstractVerifyResolverHandle handle) {
        return null;
    }

    @Override
    public AbstractVerifyResolverHandle verifyHandleSupportFactory(Method targetMethod, Object arg, Field field) {
        return null;
    }
}
