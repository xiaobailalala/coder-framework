package com.coder.framework.validate.config;

import com.coder.framework.validate.resolver.AbstractVerifyProcess;
import com.coder.framework.validate.support.AbstractVerifyRegistrySupport;
import com.coder.framework.validate.support.AbstractVerifyResolverScanSupport;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
 * Build File @date: 2019/9/12 19:23
 * @version 1.0
 * @description
 */
public class VerifyResolverScan extends AbstractVerifyResolverScanSupport implements AbstractVerifyRegistrySupport {

    private List<AbstractVerifyProcess> abstractVerifyProcesses = new LinkedList<>();

    @Override
    protected void scanResultCallback(int order, AbstractVerifyProcess verifyProcess) {
        super.abstractVerifyProcessTemps.add(new AbstractVerifyProcessTemp(verifyProcess, order));
    }

    VerifyResolverScan() {
        sortVerifyResolver();
        registryVerifyResolver();
    }

    private void sortVerifyResolver() {
        this.abstractVerifyProcesses.addAll(super.abstractVerifyProcessTemps.stream()
                .sorted(Comparator.comparingInt(AbstractVerifyProcessTemp::getOrder))
                .map(AbstractVerifyProcessTemp::getAbstractVerifyProcess)
                .collect(Collectors.toList()));
    }

    private void registryVerifyResolver() {
        for (AbstractVerifyProcess abstractVerifyProcess : this.abstractVerifyProcesses) {
            registryVerifyProcess(abstractVerifyProcess);
        }
    }

}
