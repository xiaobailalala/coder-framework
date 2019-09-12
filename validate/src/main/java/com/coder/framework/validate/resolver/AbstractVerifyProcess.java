package com.coder.framework.validate.resolver;

import com.coder.framework.validate.exception.VerifyBaseException;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
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
     * Determine if conditions are met that can be processed using the corresponding annotation
     * @param method Cut - through object method
     * @param args Cut - through object args
     * @return Returns true, executes the processor, otherwise does not
     */
    public abstract boolean methodFilter(Method method, Object[] args);

    /**
     * On the basis of the former through the corresponding logic processing
     * @param joinPoint Cut - through object
     * @param abstractVerifyProcesses A child object is validated by the former
     * @return Returns a data validation exception captured during processing, or null if no exception exists
     */
    public abstract VerifyBaseException coreProcessingMethod(JoinPoint joinPoint, List<AbstractVerifyProcess> abstractVerifyProcesses);

}
