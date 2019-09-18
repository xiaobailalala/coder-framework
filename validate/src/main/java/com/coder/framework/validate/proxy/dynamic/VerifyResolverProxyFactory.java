package com.coder.framework.validate.proxy.dynamic;

import com.coder.framework.validate.exception.VerifyBaseException;
import com.coder.framework.validate.handle.AbstractVerifyAdapter;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
 * Build File @date: 2019/9/16 1:34
 * @version 1.0
 * @description TODO
 */
public class VerifyResolverProxyFactory {

    private AbstractVerifyAdapter target;

    private VerifyResolverProxyFactory() {
    }

    public static VerifyResolverProxyFactory initProxyFactory() {
        return new VerifyResolverProxyFactory();
    }

    public VerifyResolverProxyFactory optionTargetProcess(AbstractVerifyAdapter target) {
        this.target = target;
        return this;
    }

    public AbstractVerifyAdapter getProxyInstance() {
        return (AbstractVerifyAdapter) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), (proxy, method, args) -> {
                    String targetMethodName = "";
                    for (Method declaredMethod : AbstractVerifyAdapter.class.getDeclaredMethods()) {
                        if (VerifyBaseException.class.equals(declaredMethod.getReturnType())) {
                            targetMethodName = declaredMethod.getName();
                        }
                    }
                    Object invoke = method.invoke(target, args);
                    if (targetMethodName.equals(method.getName()) && invoke instanceof VerifyBaseException) {
                        throw (Throwable) invoke;
                    }
                    return invoke;
                });
    }

}
