package com.coder.framework.validate.support;

import com.coder.framework.validate.resolver.AbstractVerifyProcess;

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

    private static Map<String, VerifySingletonRegistry> verifySingletonRegistryCache = new HashMap<>(8);

    static AbstractVerifyProcess getVerifyProcess(Class<? extends AbstractVerifyProcess> verifyClazz) {
        return getVerifyRegistry().getSingleton(verifyClazz);
    }

    static AbstractVerifyProcess getVerifyProcess(String verifyName) {
        return getVerifyRegistry().getSingleton(verifyName);
    }

    static AbstractVerifyProcess registryVerifyProcess(String verifyName, AbstractVerifyProcess singletonObject) {
        return getVerifyRegistry().registrySingleton(verifyName, singletonObject);
    }

    static void registryVerifyProcess(AbstractVerifyProcess singletonObject) {
        getVerifyRegistry().registrySingleton(singletonObject.getClass().getName(), singletonObject);
    }

    static Set<String> getVerifyProcessName() {
        return getVerifyRegistry().getRegisteredVerifyCache();
    }

    static List<AbstractVerifyProcess> getVerifyProcessInstance() {
        List<AbstractVerifyProcess> verifyProcesses = new LinkedList<>();
        for (String verify : getVerifyProcessName()) {
            verifyProcesses.add(getVerifyProcess(verify));
        }
        return verifyProcesses;
    }

    @SuppressWarnings("all")
    private static VerifySingletonRegistry getVerifyRegistry() {
        VerifySingletonRegistry verifySingletonRegistry = verifySingletonRegistryCache.get(VerifySingletonRegistry.class.getName());
        if (verifySingletonRegistry == null) {
            synchronized (VerifyRegistrySupport.class) {
                if (verifySingletonRegistry == null) {
                    verifySingletonRegistry = new VerifySingletonRegistry();
                    verifySingletonRegistryCache.put(verifySingletonRegistry.getClass().getName(), verifySingletonRegistry);
                }
            }
        }
        return verifySingletonRegistry;
    }

}
