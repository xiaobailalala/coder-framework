package com.coder.framework.validate.proxy.cglib;

import com.coder.framework.validate.adapter.AbstractVerifyAdapter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

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
 * Build File @date: 2019/9/16 1:34
 * @version 1.0
 * @description TODO
 */
public class VerifyResolverProxyFactory implements MethodInterceptor {

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
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (AbstractVerifyAdapter) enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
        return method.invoke(target, args);
    }

}
