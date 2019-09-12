package com.coder.framework.validate.resolver;

import com.coder.framework.validate.annotation.VerifyNotNull;
import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.exception.VerifyBaseException;
import org.aspectj.lang.JoinPoint;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class VerifyNotNullResolver extends AbstractVerifyProcess {

    @Override
    public boolean methodFilter(Method method, Object[] args) {
        List<Object> collect = Arrays.stream(args).filter((item) -> {
            VerifyNotNull verifyNotNull = item.getClass().getDeclaredAnnotation(VerifyNotNull.class);
            return !ObjectUtils.isEmpty(verifyNotNull);
        }).collect(Collectors.toList());
        return collect.size() != 0;
    }

    @Override
    public VerifyBaseException coreProcessingMethod(JoinPoint joinPoint, List<AbstractVerifyProcess> abstractVerifyProcesses) {
        return null;
    }
}
