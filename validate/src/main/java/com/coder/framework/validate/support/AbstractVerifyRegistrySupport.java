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
@SuppressWarnings("unused")
public interface AbstractVerifyRegistrySupport {

    /**
     * Get the corresponding target instance from the singleton factory
     * @param verifyClazz Object instance bytecode
     * @return The corresponding target instance
     */
    default AbstractVerifyAdapter getVerifyProcessTarget(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        return VerifyRegistrySupport.getVerifyProcessTarget(verifyClazz);
    }

    /**
     * Get the corresponding target instance from the singleton factory
     * @param verifyName Object instance name
     * @return The corresponding target instance
     */
    default AbstractVerifyAdapter getVerifyProcessTarget(String verifyName) {
        return VerifyRegistrySupport.getVerifyProcessTarget(verifyName);
    }

    /**
     * Get the corresponding proxy instance from the singleton factory
     * @param verifyClazz Object instance bytecode
     * @return The corresponding proxy instance
     */
    default AbstractVerifyAdapter getVerifyProcessProxy(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        return VerifyRegistrySupport.getVerifyProcessProxy(verifyClazz);
    }

    /**
     * Get the corresponding proxy instance from the singleton factory
     * @param verifyName Object instance name
     * @return The corresponding proxy instance
     */
    default AbstractVerifyAdapter getVerifyProcessProxy(String verifyName) {
        return VerifyRegistrySupport.getVerifyProcessProxy(verifyName);
    }

    /**
     * Register the instance to the singleton factory
     * @param verifyName the instance name
     * @param singletonObject the instance object
     * @return the corresponding target instance from singleton factory
     */
    default AbstractVerifyAdapter registryVerifyProcess(String verifyName, AbstractVerifyAdapter singletonObject) {
        return VerifyRegistrySupport.registryVerifyProcess(verifyName, singletonObject);
    }

    /**
     * Register the instance to the singleton factory
     * @param singletonObject the instance object
     */
    default void registryVerifyProcess(AbstractVerifyAdapter singletonObject) {
        VerifyRegistrySupport.registryVerifyProcess(singletonObject);
    }

    /**
     * Gets the list of instance names corresponding to the instance,
     * which determines the order of the instances
     * @return The list of instance names
     */
    default Set<Class<? extends AbstractVerifyAdapter>> getVerifyProcessName() {
        return VerifyRegistrySupport.getVerifyProcessName();
    }

    /**
     * Gets the list of target instance corresponding to the instance, which determines the order of the instances
     * @return the list of target instances
     */
    default List<AbstractVerifyAdapter> getVerifyProcessTargetInstance() {
        return VerifyRegistrySupport.getVerifyProcessTargetInstance();
    }

    /**
     * Gets the list of proxy instance corresponding to the instance, which determines the order of the instances
     * @return the list of proxy instances
     */
    default List<AbstractVerifyAdapter> getVerifyProcessProxyInstance() {
        return VerifyRegistrySupport.getVerifyProcessProxyInstance();
    }

    /**
     * Gets the target instance based on the proxy instance
     * @param proxy the proxy instance
     * @return the target instance
     */
    default AbstractVerifyAdapter getTarget(AbstractVerifyAdapter proxy) {
        return VerifyRegistrySupport.getTarget(proxy);
    }

}
