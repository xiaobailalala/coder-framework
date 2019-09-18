package com.coder.framework.validate.exception.handle;

import com.coder.framework.validate.common.ResponseBuilderManager;
import com.coder.framework.validate.exception.verification.InvalidDataDefinitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
 * Build File @date: 2019/9/5 18:50
 * @version 1.0
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHandle implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);
    private ApplicationContext applicationContext;

    @ExceptionHandler(InvalidDataDefinitionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object clientTokenExceptionHandler(InvalidDataDefinitionException ex) {
        logger.error(ex.getMessage());
        return getResponseBuilderManagerBean().builder()
                .conditionBuilder(true)
                .dataBuilder(null)
                .messageBuilder(ex.getMessage())
                .statusBuilder(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .generatorResponseEntity();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ResponseBuilderManager getResponseBuilderManagerBean() {
        return this.applicationContext.getBean(ResponseBuilderManager.class);
    }

}
