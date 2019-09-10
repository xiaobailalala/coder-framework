package com.coder.framework.validate.resolver;

import com.coder.framework.validate.exception.VerifyBaseException;
import org.aspectj.lang.JoinPoint;

import java.util.List;

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
 * Build File @date: 2019/9/10 17:31
 * @version 1.0
 * @description
 */
public abstract class AbstractVerifyProcess {

    /**
     * das
     * @param joinPoint asd
     * @return adsa
     */
    public abstract boolean methodFilter(JoinPoint joinPoint);

    /**
     * asd
     * @param joinPoint aasd
     * @param abstractVerifyProcesses asda
     * @return das
     */
    public abstract VerifyBaseException coreProcessingMethod(JoinPoint joinPoint, List<AbstractVerifyProcess> abstractVerifyProcesses);

}
