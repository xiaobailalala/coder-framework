package com.coder.framework.validate.handle;

import com.coder.framework.validate.config.VerifyInterpreterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

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
//@RestControllerAdvice(basePackages = this.scanPackage)
public class GlobalExceptionHandle extends ApplicationObjectSupport {

//    final String scanPackage = "1234";
    final String[] scanPackage = Objects.requireNonNull(getApplicationContext()).getBean(VerifyInterpreterRegistry.class).getPackageContainer().toArray(new String[0]);

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

//    @ExceptionHandler(InvalidDataDefinitionException.class)
//    public ResultApi clientTokenExceptionHandler(HttpServletResponse response, InvalidDataDefinitionException ex) {
//        response.setStatus(403);
//        logger.error(ex.getMessage(),ex);
//        return ResultApi.build(ex.getStatus(), ex.getMessage());
//    }

}
