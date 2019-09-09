package com.coder.framework.validate.aspect;

import com.coder.framework.validate.annotation.EnableVerifyResponseEntity;
import com.coder.framework.validate.exception.InvalidDataDefinitionException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.Annotation;
import java.util.Arrays;

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
 * Build File @date: 2019/9/5 0:05
 * @version 1.0
 * @description TODO
 */
@Aspect
public class VerifyMethodArgumentResolver {

    @Pointcut("@annotation(com.coder.framework.validate.annotation.Verify)")
    public void doAspect() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ControllerAdvice)")
    public void doEnableVerify() {}

    @Before("doAspect()")
    public void doBefore(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            Class aClass = arg.getClass();

            if (arg.toString().equals("1")) {
                throw new InvalidDataDefinitionException("hahaha");
            }
            Annotation annotation = aClass.getAnnotation(EnableVerifyResponseEntity.class);
        }
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    @Before("doEnableVerify()")
    public void doEnableVerify(JoinPoint joinPoint) {
        System.out.println(12312312);
    }

    private boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>)obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

}
