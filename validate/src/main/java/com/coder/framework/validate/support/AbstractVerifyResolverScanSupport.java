package com.coder.framework.validate.support;

import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.common.AbstractPackageScanner;
import com.coder.framework.validate.adapter.AbstractVerifyAdapter;

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
    protected abstract void scanResultCallback(int order, AbstractVerifyAdapter verifyProcess);

    protected AbstractVerifyResolverScanSupport() {
        scanAccordingToAbstractVerifyProcess();
        otherScan();
    }

    private void scanAccordingToAbstractVerifyProcess() {
        new AbstractPackageScanner() {
            @Override
            public void dealClass(Class<?> clazz) {
                if (AbstractVerifyAdapter.class.isAssignableFrom(clazz)) {
                    VerifyOrder declaredAnnotation = clazz.getDeclaredAnnotation(VerifyOrder.class);
                    try {
                        scanResultCallback(declaredAnnotation.value(), (AbstractVerifyAdapter) clazz.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.packageScanner(AbstractVerifyAdapter.class);
    }

    private void otherScan() {}

    protected class AbstractVerifyProcessTemp {
        private AbstractVerifyAdapter abstractVerifyAdapter;
        private int order;

        public AbstractVerifyProcessTemp(AbstractVerifyAdapter abstractVerifyAdapter, int order) {
            this.abstractVerifyAdapter = abstractVerifyAdapter;
            this.order = order;
        }

        public AbstractVerifyAdapter getAbstractVerifyAdapter() {
            return abstractVerifyAdapter;
        }

        public int getOrder() {
            return order;
        }
    }

}
