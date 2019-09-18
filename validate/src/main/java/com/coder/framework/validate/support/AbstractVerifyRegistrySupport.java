package com.coder.framework.validate.support;

import com.coder.framework.validate.handle.AbstractVerifyAdapter;

import java.util.List;
import java.util.Set;

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
 * Build File @date: 2019/9/12 19:44
 * @version 1.0
 * @description
 */
public interface AbstractVerifyRegistrySupport {

    default AbstractVerifyAdapter getVerifyProcessTarget(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        return VerifyRegistrySupport.getVerifyProcessTarget(verifyClazz);
    }

    default AbstractVerifyAdapter getVerifyProcessTarget(String verifyName) {
        return VerifyRegistrySupport.getVerifyProcessTarget(verifyName);
    }

    default AbstractVerifyAdapter getVerifyProcessProxy(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        return VerifyRegistrySupport.getVerifyProcessProxy(verifyClazz);
    }

    default AbstractVerifyAdapter getVerifyProcessProxy(String verifyName) {
        return VerifyRegistrySupport.getVerifyProcessProxy(verifyName);
    }

    default AbstractVerifyAdapter registryVerifyProcess(String verifyName, AbstractVerifyAdapter singletonObject) {
        return VerifyRegistrySupport.registryVerifyProcess(verifyName, singletonObject);
    }

    default void registryVerifyProcess(AbstractVerifyAdapter singletonObject) {
        VerifyRegistrySupport.registryVerifyProcess(singletonObject);
    }

    default Set<Class<? extends AbstractVerifyAdapter>> getVerifyProcessName() {
        return VerifyRegistrySupport.getVerifyProcessName();
    }

    default List<AbstractVerifyAdapter> getVerifyProcessTargetInstance() {
        return VerifyRegistrySupport.getVerifyProcessTargetInstance();
    }

    default List<AbstractVerifyAdapter> getVerifyProcessProxyInstance() {
        return VerifyRegistrySupport.getVerifyProcessProxyInstance();
    }

    default AbstractVerifyAdapter getTarget(AbstractVerifyAdapter proxy) {
        return VerifyRegistrySupport.getTarget(proxy);
    }

}
