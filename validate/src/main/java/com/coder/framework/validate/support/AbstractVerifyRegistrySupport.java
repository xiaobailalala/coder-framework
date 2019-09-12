package com.coder.framework.validate.support;

import com.coder.framework.validate.resolver.AbstractVerifyProcess;

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

    default AbstractVerifyProcess getVerifyProcess(Class<? extends AbstractVerifyProcess> verifyClazz) {
        return VerifyRegistrySupport.getVerifyProcess(verifyClazz);
    }

    default AbstractVerifyProcess getVerifyProcess(String verifyName) {
        return VerifyRegistrySupport.getVerifyProcess(verifyName);
    }

    default AbstractVerifyProcess registryVerifyProcess(String verifyName, AbstractVerifyProcess singletonObject) {
        return VerifyRegistrySupport.registryVerifyProcess(verifyName, singletonObject);
    }

    default void registryVerifyProcess(AbstractVerifyProcess singletonObject) {
        VerifyRegistrySupport.registryVerifyProcess(singletonObject);
    }

    default Set<String> getVerifyProcessName() {
        return VerifyRegistrySupport.getVerifyProcessName();
    }

    default List<AbstractVerifyProcess> getVerifyProcessInstance() {
        return VerifyRegistrySupport.getVerifyProcessInstance();
    }

}
