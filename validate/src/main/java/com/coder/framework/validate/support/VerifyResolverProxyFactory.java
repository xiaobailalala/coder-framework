package com.coder.framework.validate.support;

import com.coder.framework.validate.resolver.AbstractVerifyProcess;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
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
public class VerifyResolverProxyFactory implements MethodInterceptor {

    private AbstractVerifyProcess target;

    private VerifyResolverProxyFactory() {
    }

    public static VerifyResolverProxyFactory initProxyFactory() {
        return new VerifyResolverProxyFactory();
    }

    public VerifyResolverProxyFactory optionTargetProcess(AbstractVerifyProcess target) {
        this.target = target;
        return this;
    }

    public AbstractVerifyProcess getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (AbstractVerifyProcess) enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
        System.out.println(Arrays.toString(args));
        return method.invoke(target, args);
    }

}
