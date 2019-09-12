package com.coder.framework.validate.support;

import com.coder.framework.validate.exception.VerifyFrameworkRegistryException;
import com.coder.framework.validate.resolver.AbstractVerifyProcess;
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
public class VerifySingletonRegistry {

    private final Map<String, AbstractVerifyProcess> verifyProcessCache = new ConcurrentHashMap<>(128);
    private final Set<String> registeredVerifyCache = new LinkedHashSet<>(128);

    public AbstractVerifyProcess getSingleton(Class<? extends AbstractVerifyProcess> verifyClazz) {
        if (ObjectUtils.isEmpty(verifyClazz)) {
            throw new VerifyFrameworkRegistryException("Object must not be null");
        }
        return getSingleton(verifyClazz.getName());
    }

    public AbstractVerifyProcess getSingleton(String verifyName) {
        AbstractVerifyProcess abstractVerifyProcess = this.verifyProcessCache.get(verifyName);
        if (ObjectUtils.isEmpty(abstractVerifyProcess)) {
            throw new VerifyFrameworkRegistryException("The object has not yet been registered");
        }
        return abstractVerifyProcess;
    }

    public AbstractVerifyProcess registrySingleton(String verifyName, AbstractVerifyProcess singletonObject) {
        AbstractVerifyProcess singleton;
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

    private AbstractVerifyProcess addSingleton(String verifyName, AbstractVerifyProcess singletonObject) {
        synchronized (this.verifyProcessCache) {
            this.verifyProcessCache.put(verifyName, singletonObject);
            this.registeredVerifyCache.add(verifyName);
        }
        return singletonObject;
    }

    public Set<String> getRegisteredVerifyCache() {
        return registeredVerifyCache;
    }
}
