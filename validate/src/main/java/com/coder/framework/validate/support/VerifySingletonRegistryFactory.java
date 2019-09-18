package com.coder.framework.validate.support;

import com.coder.framework.validate.exception.VerifyFrameworkRegistryException;
import com.coder.framework.validate.proxy.dynamic.VerifyResolverProxyFactory;
import com.coder.framework.validate.handle.AbstractVerifyAdapter;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
 * Build File @date: 2019/9/12 15:31
 * @version 1.0
 * @description
 */
class VerifySingletonRegistryFactory {

    private final Map<String, AbstractVerifyAdapter> verifyProcessCache = new ConcurrentHashMap<>(128);
    private final Map<String, AbstractVerifyAdapter> proxyVerifyProcessCache = new ConcurrentHashMap<>(128);
    private final Set<Class<? extends AbstractVerifyAdapter>> registeredVerifyCache = new LinkedHashSet<>(128);

    AbstractVerifyAdapter getSingletonTarget(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        if (ObjectUtils.isEmpty(verifyClazz)) {
            throw new VerifyFrameworkRegistryException("Object must not be null");
        }
        return getSingletonTarget(verifyClazz.getName());
    }

    AbstractVerifyAdapter getSingletonTarget(String verifyName) {
        AbstractVerifyAdapter abstractVerifyAdapter = this.verifyProcessCache.get(verifyName);
        if (ObjectUtils.isEmpty(abstractVerifyAdapter)) {
            throw new VerifyFrameworkRegistryException("The object has not yet been registered");
        }
        return abstractVerifyAdapter;
    }

    AbstractVerifyAdapter getSingletonProxy(Class<? extends AbstractVerifyAdapter> verifyClazz) {
        if (ObjectUtils.isEmpty(verifyClazz)) {
            throw new VerifyFrameworkRegistryException("Object must not be null");
        }
        return getSingletonProxy(verifyClazz.getName());
    }

    AbstractVerifyAdapter getSingletonProxy(String verifyName) {
        AbstractVerifyAdapter abstractVerifyAdapter = this.proxyVerifyProcessCache.get(verifyName);
        if (ObjectUtils.isEmpty(abstractVerifyAdapter)) {
            throw new VerifyFrameworkRegistryException("The object has not yet been registered");
        }
        return abstractVerifyAdapter;
    }

    AbstractVerifyAdapter registrySingletonTarget(String verifyName, AbstractVerifyAdapter singletonObject) {
        AbstractVerifyAdapter singleton;
        synchronized (this.verifyProcessCache) {
            singleton = this.verifyProcessCache.get(verifyName);
            if (singleton != null) {
                throw new VerifyFrameworkRegistryException("Could not register object [" + singletonObject +
                        "] under bean name '" + verifyName + "': there is already object [" + singleton + "] bound");
            }
            singleton = addSingleton(verifyName, singletonObject);
        }
        return singleton;
    }

    private AbstractVerifyAdapter addSingleton(String verifyName, AbstractVerifyAdapter singletonObject) {
        VerifyResolverProxyFactory proxyFactory = VerifyResolverProxyFactory.initProxyFactory();
        synchronized (this.verifyProcessCache) {
            this.verifyProcessCache.put(verifyName, singletonObject);
            this.registeredVerifyCache.add(singletonObject.getClass());
            this.proxyVerifyProcessCache.put(verifyName, proxyFactory.optionTargetProcess(singletonObject).getProxyInstance());
        }
        return singletonObject;
    }

    Set<Class<? extends AbstractVerifyAdapter>> getRegisteredVerifyCache() {
        return registeredVerifyCache;
    }
}
