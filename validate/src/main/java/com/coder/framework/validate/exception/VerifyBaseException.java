package com.coder.framework.validate.exception;

import org.springframework.http.HttpStatus;

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
 * Build File @date: 2019/9/5 18:52
 * @version 1.0
 * @description
 */
public class VerifyBaseException extends RuntimeException {

    private int status = HttpStatus.OK.value();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public VerifyBaseException() {
    }

    public VerifyBaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public VerifyBaseException(String message) {
        super(message);
    }

    public VerifyBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyBaseException(Throwable cause) {
        super(cause);
    }

    public VerifyBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
