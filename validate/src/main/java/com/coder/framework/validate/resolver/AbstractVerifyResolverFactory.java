package com.coder.framework.validate.resolver;

import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.common.AbstractPackageScanner;
import com.coder.framework.validate.exception.VerifyBaseException;
import com.coder.framework.validate.support.AbstractVerifyRegistrySupport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.LinkedList;
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
 * Build File @date: 2019/9/10 19:09
 * @version 1.0
 * @description
 */
public abstract class AbstractVerifyResolverFactory implements AbstractVerifyRegistrySupport {

    private List<AbstractVerifyProcess> abstractVerifyProcesses = new LinkedList<>();

    /**
     * The callback method after execution by the handler that parses the annotation
     *
     * @param throwable An exception is thrown if an exception occurs in the process of resolving the target
     */
    public abstract void processAndGetResult(RuntimeException throwable);

    void createVerifyResolverFactory(JoinPoint joinPoint) {
        for (AbstractVerifyProcess abstractVerifyProcess : getVerifyProcessInstance()) {
            if (abstractVerifyProcess.methodFilter(((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs())) {
                this.abstractVerifyProcesses.add(abstractVerifyProcess);
            }
        }
        verifyResolverProcessor(joinPoint);
    }

    private void verifyResolverProcessor(JoinPoint joinPoint) {
        for (AbstractVerifyProcess abstractVerifyProcess : this.abstractVerifyProcesses) {
            VerifyBaseException verifyBaseException = abstractVerifyProcess
                    .coreProcessingMethod(joinPoint, this.abstractVerifyProcesses);
            if (!ObjectUtils.isEmpty(verifyBaseException)) {
                processAndGetResult(verifyBaseException);
            }
        }
    }

}
