package com.coder.framework.validate.resolver;

import com.coder.framework.validate.annotation.VerifyOrder;
import com.coder.framework.validate.exception.VerifyBaseException;
import org.aspectj.lang.JoinPoint;

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
 * Build File @date: 2019/9/10 20:22
 * @version 1.0
 * @description
 */
@VerifyOrder(2)
public class VerifyEntityResolver extends AbstractVerifyProcess {

    @Override
    public boolean methodFilter(JoinPoint joinPoint) {
        return true;
    }

    @Override
    public VerifyBaseException coreProcessingMethod(JoinPoint joinPoint, List<AbstractVerifyProcess> abstractVerifyProcesses) {
        return null;
    }
}
