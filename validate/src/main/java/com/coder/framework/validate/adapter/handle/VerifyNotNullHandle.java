package com.coder.framework.validate.adapter.handle;

import com.coder.framework.validate.adapter.AbstractVerifyResolverHandle;
import com.coder.framework.validate.annotation.VerifyNotNull;
import com.coder.framework.validate.exception.VerifyBaseException;
import com.coder.framework.validate.exception.verification.InvalidDataDefinitionException;
import com.coder.framework.validate.util.MethodParameter;
import com.sun.xml.internal.ws.api.model.MEP;
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
 * Build File @date: 2019/9/19 16:51
 * @version 1.0
 * @description
 */
public class VerifyNotNullHandle extends AbstractVerifyResolverHandle {

    public VerifyNotNullHandle(MethodParameter targetMethod, Object arg, Field field) {
        super(targetMethod, arg, field);
    }

    @Override
    protected VerifyBaseException doResolver(MethodParameter method, Object arg) {
        if (method.hasParameterAnnotation(VerifyNotNull.class)) {
            if (ObjectUtils.isEmpty(arg)) {
                VerifyNotNull verifyNotNull = method.getParameter().getAnnotation(VerifyNotNull.class);
                return new InvalidDataDefinitionException(verifyNotNull.value());
            }
        }
        return super.doResolver(method, arg);
    }
}
