package com.coder.framework.validate.aspect;

import com.coder.framework.validate.annotation.Verify;
import com.coder.framework.validate.exception.InvalidDataDefinitionException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ObjectUtils;

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
        if (!ObjectUtils.isEmpty(joinPoint.getArgs())) {
            Verify annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Verify.class);
            for (Object arg : joinPoint.getArgs()) {
                if (arg.toString().equals("1")) {
                    throw new InvalidDataDefinitionException(annotation.msg());
                }
            }
            System.out.println(Arrays.toString(joinPoint.getArgs()));
        }
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
