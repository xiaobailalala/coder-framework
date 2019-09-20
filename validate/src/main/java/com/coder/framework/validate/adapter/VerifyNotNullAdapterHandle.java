package com.coder.framework.validate.adapter;

import com.coder.framework.validate.adapter.handle.VerifyNotNullHandle;
import com.coder.framework.validate.annotation.verify.VerifyEntity;
import com.coder.framework.validate.annotation.verify.VerifyNotNull;
import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.exception.VerifyBaseException;
import com.coder.framework.validate.util.CoreStaticUtil;
import com.coder.framework.validate.util.MethodParameter;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;

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
public class VerifyNotNullAdapterHandle implements AbstractVerifyAdapter {

    @Override
    public boolean methodFilter(MethodParameter method, Object arg, Field field) {
        if (method.hasParameterAnnotation(VerifyNotNull.class)) {
            return true;
        }
        return !CoreStaticUtil.isPrimitive(arg) &&
                arg.getClass().isAnnotationPresent(VerifyEntity.class) &&
                !ObjectUtils.isEmpty(field) &&
                field.isAnnotationPresent(VerifyNotNull.class);
    }

    @Override
    public VerifyBaseException coreProcessingMethod(AbstractVerifyResolverHandle handle) {
        return handle.executeAndThrow(
                handle.doResolver(handle.method, handle.arg),
                handle.doResolver(handle.arg, handle.field));
    }

    @Override
    public AbstractVerifyResolverHandle verifyHandleSupportFactory(MethodParameter targetMethod, Object arg, Field field) {
        return new VerifyNotNullHandle(targetMethod, arg, field);
    }


}
