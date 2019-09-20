package com.coder.framework.validate.adapter;

import com.coder.framework.validate.exception.VerifyBaseException;
import com.coder.framework.validate.exception.verification.InvalidDataDefinitionException;
import com.coder.framework.validate.util.MethodParameter;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
 * Build File @date: 2019/9/19 10:28
 * @version 1.0
 * @description TODO
 */
@SuppressWarnings("unused")
public abstract class AbstractVerifyResolverHandle {

    protected MethodParameter method;
    protected Object arg;
    protected Field field;

    public AbstractVerifyResolverHandle(MethodParameter targetMethod, Object arg, Field field) {
        this.method = targetMethod;
        this.arg = arg;
        this.field = field;
    }

    /**
     * Specifies the execution scheme for the validation controller,
     * and the method can be selected based on the valid parameters provided
     *
     * @param method The parameters [method] provided by the verifier
     * @param arg    The parameters [arg] provided by the verifier
     * @param field  The parameters [field] provided by the verifier
     * @return Check the exception {@link InvalidDataDefinitionException} generated during the process,
     * check the exception specifically, and return {@code null} if there is no exception in the processing result
     */
    protected VerifyBaseException doResolver(MethodParameter method, Object arg, Field field) {
        return null;
    }

    /**
     * Specifies the execution scheme for the validation controller,
     * and the method can be selected based on the valid parameters provided
     *
     * @param method The parameters [method] provided by the verifier
     * @return Check the exception {@link InvalidDataDefinitionException} generated during the process,
     * check the exception specifically, and return {@code null} if there is no exception in the processing result
     */
    protected VerifyBaseException doResolver(MethodParameter method) {
        return null;
    }

    /**
     * Specifies the execution scheme for the validation controller,
     * and the method can be selected based on the valid parameters provided
     *
     * @param arg The parameters [arg] provided by the verifier
     * @return Check the exception {@link InvalidDataDefinitionException} generated during the process,
     * check the exception specifically, and return {@code null} if there is no exception in the processing result
     */
    protected VerifyBaseException doResolver(Object arg) {
        return null;
    }

    /**
     * Specifies the execution scheme for the validation controller,
     * and the method can be selected based on the valid parameters provided
     *
     * @param field The parameters [field] provided by the verifier
     * @return Check the exception {@link InvalidDataDefinitionException} generated during the process,
     * check the exception specifically, and return {@code null} if there is no exception in the processing result
     */
    protected VerifyBaseException doResolver(Field field) {
        return null;
    }

    /**
     * Specifies the execution scheme for the validation controller,
     * and the method can be selected based on the valid parameters provided
     *
     * @param method The parameters [method] provided by the verifier
     * @param arg    The parameters [arg] provided by the verifier
     * @return Check the exception {@link InvalidDataDefinitionException} generated during the process,
     * check the exception specifically, and return {@code null} if there is no exception in the processing result
     */
    protected VerifyBaseException doResolver(MethodParameter method, Object arg) {
        return null;
    }

    /**
     * Specifies the execution scheme for the validation controller,
     * and the method can be selected based on the valid parameters provided
     *
     * @param method The parameters [method] provided by the verifier
     * @param field  The parameters [field] provided by the verifier
     * @return Check the exception {@link InvalidDataDefinitionException} generated during the process,
     * check the exception specifically, and return {@code null} if there is no exception in the processing result
     */
    protected VerifyBaseException doResolver(MethodParameter method, Field field) {
        return null;
    }

    /**
     * Specifies the execution scheme for the validation controller,
     * and the method can be selected based on the valid parameters provided
     *
     * @param arg   The parameters [arg] provided by the verifier
     * @param field The parameters [field] provided by the verifier
     * @return Check the exception {@link InvalidDataDefinitionException} generated during the process,
     * check the exception specifically, and return {@code null} if there is no exception in the processing result
     */
    protected VerifyBaseException doResolver(Object arg, Field field) {
        return null;
    }

    protected VerifyBaseException executeAndThrow(VerifyBaseException... exception) {
        for (VerifyBaseException verifyBaseException : exception) {
            if (!ObjectUtils.isEmpty(verifyBaseException)) {
                return verifyBaseException;
            }
        }
        return null;
    }

}
