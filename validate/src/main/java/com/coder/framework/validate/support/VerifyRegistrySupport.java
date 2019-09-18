package com.coder.framework.validate.support;

import com.coder.framework.validate.handle.AbstractVerifyAdapter;

import java.lang.reflect.Field;
import java.util.*;

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
 * Build File @date: 2019/9/12 17:39
 * @version 1.0
 * @description
 */
class VerifyRegistrySupport {

    private static Map<String, VerifySingletonRegistryFactory> verifySingletonRegistryCache = new HashMap<>(8);

    static AbstractVerifyAdapter getVerifyProcessTarget(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        return getVerifyRegistry().getSingletonTarget(verifyClazz);
    }

    static AbstractVerifyAdapter getVerifyProcessTarget(String verifyName) {
        return getVerifyRegistry().getSingletonTarget(verifyName);
    }

    static AbstractVerifyAdapter getVerifyProcessProxy(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        return getVerifyRegistry().getSingletonProxy(verifyClazz);
    }

    static AbstractVerifyAdapter getVerifyProcessProxy(String verifyName) {
        return getVerifyRegistry().getSingletonProxy(verifyName);
    }

    static AbstractVerifyAdapter registryVerifyProcess(String verifyName, AbstractVerifyAdapter singletonObject) {
        return getVerifyRegistry().registrySingletonTarget(verifyName, singletonObject);
    }

    static void registryVerifyProcess(AbstractVerifyAdapter singletonObject) {
        getVerifyRegistry().registrySingletonTarget(singletonObject.getClass().getName(), singletonObject);
    }

    static Set<Class<? extends AbstractVerifyAdapter>> getVerifyProcessName() {
        return getVerifyRegistry().getRegisteredVerifyCache();
    }

    static List<AbstractVerifyAdapter> getVerifyProcessTargetInstance() {
        List<AbstractVerifyAdapter> verifyProcesses = new LinkedList<>();
        for (Class<? extends AbstractVerifyAdapter> verify : getVerifyProcessName()) {
            verifyProcesses.add(getVerifyProcessTarget(verify));
        }
        return verifyProcesses;
    }

    static List<AbstractVerifyAdapter> getVerifyProcessProxyInstance() {
        List<AbstractVerifyAdapter> verifyProcesses = new LinkedList<>();
        for (Class<? extends AbstractVerifyAdapter> verify : getVerifyProcessName()) {
            verifyProcesses.add(getVerifyProcessProxy(verify));
        }
        return verifyProcesses;
    }

    static AbstractVerifyAdapter getTarget(AbstractVerifyAdapter proxy) {
        AbstractVerifyAdapter targetInstance = null;
        try {
            Field field = proxy.getClass().getSuperclass().getDeclaredField("h");
            field.setAccessible(true);
            Object proxyInstance = field.get(proxy);
            Field targetField = proxyInstance.getClass().getDeclaredField("arg$1");
            targetField.setAccessible(true);
            Object target = targetField.get(proxyInstance);
            Field resField = target.getClass().getDeclaredField("target");
            resField.setAccessible(true);
            targetInstance = (AbstractVerifyAdapter) resField.get(target);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return targetInstance;
    }

    @SuppressWarnings("all")
    private static VerifySingletonRegistryFactory getVerifyRegistry() {
        VerifySingletonRegistryFactory verifySingletonRegistry = verifySingletonRegistryCache.get(VerifySingletonRegistryFactory.class.getName());
        if (verifySingletonRegistry == null) {
            synchronized (VerifyRegistrySupport.class) {
                if (verifySingletonRegistry == null) {
                    verifySingletonRegistry = new VerifySingletonRegistryFactory();
                    verifySingletonRegistryCache.put(verifySingletonRegistry.getClass().getName(), verifySingletonRegistry);
                }
            }
        }
        return verifySingletonRegistry;
    }

}
