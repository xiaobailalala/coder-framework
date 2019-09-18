package com.coder.framework.validate.handle;

import com.coder.framework.validate.exception.VerifyBaseException;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
 * Build File @date: 2019/9/10 17:31
 * @version 1.0
 * @description
 */
public interface AbstractVerifyAdapter {

    /**
     * Determine if conditions are met that can be processed using the corresponding annotation
     * @param targetMethod Cut - through object method
     * @param arg Cut - through object args
     * @param field Cut - through object args's fields
     * @return Returns true, executes the processor, otherwise does not
     */
    boolean methodFilter(Method targetMethod, Object arg, Field field);

    /**
     * On the basis of the former through the corresponding logic processing
//     * @param targetMethod Cut - through object method
     * @param arg Cut - through object args
     * @param field Cut - through object args's fields
     * @return Returns a data validation exception captured during processing, or {@code null} if no exception exists
     */
    VerifyBaseException coreProcessingMethod(Object arg, Field field);

}
