package com.coder.framework.validate.annotation;

import com.coder.framework.validate.config.VerifyInterpreterRegistry;
import com.coder.framework.validate.common.ResponseEntity;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

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
 * Build File @date: 2019/9/5 12:58
 * @version 1.0
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(VerifyInterpreterRegistry.class)
public @interface EnableVerify {

    String[] scanBasePackages() default {};

    Class<?> baseClazzForScan() default Class.class;

    Class<?> responseEntity() default ResponseEntity.class;

}
