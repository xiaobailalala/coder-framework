package com.coder.framework.validate.support;

import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.common.AbstractPackageScanner;
import com.coder.framework.validate.resolver.AbstractVerifyProcess;

import java.util.LinkedList;
import java.util.List;

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
 * Build File @date: 2019/9/12 19:47
 * @version 1.0
 * @description
 */
public abstract class AbstractVerifyResolverScanSupport {

    protected List<AbstractVerifyProcessTemp> abstractVerifyProcessTemps = new LinkedList<>();

    /**
     * Returns the exception resolution handler
     * @param order Exception resolution handler load order, the lower the order, the higher the identity
     * @param verifyProcess Exception resolution handler
     */
    protected abstract void scanResultCallback(int order, AbstractVerifyProcess verifyProcess);

    protected AbstractVerifyResolverScanSupport() {
        scanAccordingToAbstractVerifyProcess();
        otherScan();
    }

    private void scanAccordingToAbstractVerifyProcess() {
        new AbstractPackageScanner() {
            @Override
            public void dealClass(Class<?> klass) {
                if (klass.getSuperclass().equals(AbstractVerifyProcess.class)) {
                    VerifyOrder declaredAnnotation = klass.getDeclaredAnnotation(VerifyOrder.class);
                    try {
                        scanResultCallback(declaredAnnotation.value(), (AbstractVerifyProcess) klass.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.packageScanner(AbstractVerifyProcess.class);
    }

    private void otherScan() {}

    protected class AbstractVerifyProcessTemp {
        private AbstractVerifyProcess abstractVerifyProcess;
        private int order;

        public AbstractVerifyProcessTemp(AbstractVerifyProcess abstractVerifyProcess, int order) {
            this.abstractVerifyProcess = abstractVerifyProcess;
            this.order = order;
        }

        public AbstractVerifyProcess getAbstractVerifyProcess() {
            return abstractVerifyProcess;
        }

        public int getOrder() {
            return order;
        }
    }

}
