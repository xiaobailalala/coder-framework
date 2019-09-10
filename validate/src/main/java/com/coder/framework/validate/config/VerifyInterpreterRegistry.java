package com.coder.framework.validate.config;

import com.coder.framework.validate.annotation.EnableVerify;
import com.coder.framework.validate.annotation.EnableVerifyResponseEntity;
import com.coder.framework.validate.aspect.VerifyMethodArgumentResolver;
import com.coder.framework.validate.exception.VerifyFrameworkInitializeException;
import com.coder.framework.validate.handle.GlobalExceptionHandle;
import com.coder.framework.validate.util.ResponseEntity;
import com.coder.framework.validate.util.ResponseEntityMode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.ControllerAdviceBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;
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
 * Build File @date: 2019/9/5 15:12
 * @version 1.0
 * @description
 */
public class VerifyInterpreterRegistry extends ApplicationObjectSupport implements CommandLineRunner {

    private static Pattern packagePattern = Pattern.compile("^[a-zA-Z]+[0-9a-zA-Z_]*(\\.[a-zA-Z]+[0-9a-zA-Z_]*)*$");

    @Bean
    @DependsOn("globalExceptionHandle")
    public VerifyMethodArgumentResolver verifyMethodArgumentResolver() {
        return new VerifyMethodArgumentResolver();
    }

    @Bean(name = "globalExceptionHandle")
    @SuppressWarnings("unchecked")
    public GlobalExceptionHandle globalExceptionHandle() {
        initializeResponseEntity();
        GlobalExceptionHandle globalExceptionHandle = new GlobalExceptionHandle();
        RestControllerAdvice restControllerAdvice = globalExceptionHandle.getClass().getAnnotation(RestControllerAdvice.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(restControllerAdvice);
        try {
            Field hField = invocationHandler.getClass().getDeclaredField("memberValues");
            hField.setAccessible(true);
            Map<String, Object> memberValues = (Map<String, Object>) hField.get(invocationHandler);
            memberValues.put("basePackages", getBasePackageForExceptionHandle().toArray(new String[0]));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return globalExceptionHandle;
    }

    private void initializeResponseEntity() {
        Map<String, Object> beansWithAnnotation = Objects.requireNonNull(getApplicationContext()).getBeansWithAnnotation(EnableVerify.class);
        Set<Object> responseEntity = new LinkedHashSet<>();
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            EnableVerify enableVerify = getAnnotationFromProxyTarget(entry.getValue().getClass(), EnableVerify.class);
            if (!ObjectUtils.isEmpty(enableVerify)) {
                Class<?> entity = enableVerify.responseEntity();
                if (!ResponseEntity.class.equals(entity)) {
                    EnableVerifyResponseEntity enableVerifyResponseEntity = getAnnotationFromProxyTarget(entry.getValue().getClass(), EnableVerifyResponseEntity.class);
                    if (ObjectUtils.isEmpty(enableVerifyResponseEntity)) {
                        throw new VerifyFrameworkInitializeException("If the responseEntity parameter is declared in annotations [EnableVerify]" +
                                ", then the annotation [VerifyResponseEntity] must be declared at the same level.");
                    }
                    checkResponseEntityValidity(enableVerifyResponseEntity, entity);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void checkResponseEntityValidity(EnableVerifyResponseEntity enableVerifyResponseEntity, Class<?> entity) {
        List<ResponseEntityMode> ignoreFieldContainer = new LinkedList<>(Arrays.asList(enableVerifyResponseEntity.ignoredField()));
        ignoreFieldContainer.remove(ResponseEntityMode.DEFAULT);
        try {
            Field h = enableVerifyResponseEntity.getClass().getSuperclass().getDeclaredField("h");
            h.setAccessible(true);
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(enableVerifyResponseEntity);
            Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
            memberValues.setAccessible(true);
            Map<String, Object> fields = (Map<String, Object>) memberValues.get(invocationHandler);
            fields.remove("ignoredField");
            for (ResponseEntityMode responseEntityMode : ignoreFieldContainer) {
                fields.remove(responseEntityMode.getFieldName() + "Field");
            }

            for (Field field : entity.getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(field.getName());
            }
            for (Map.Entry<String, Object> stringObjectEntry : fields.entrySet()) {
                stringObjectEntry.getValue();
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private List<String> getBasePackageForExceptionHandle() {
        Map<String, Object> beansWithAnnotation = Objects.requireNonNull(getApplicationContext()).getBeansWithAnnotation(EnableVerify.class);
        List<String> packageList = new LinkedList<>();
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            packageList.add(entry.getValue().getClass().getPackage().getName());
            EnableVerify annotation = getAnnotationFromProxyTarget(entry.getValue().getClass(), EnableVerify.class);
            if (annotation != null) {
                if (!annotation.baseClazzForScan().equals(Class.class)) {
                    packageList.add(annotation.baseClazzForScan().getPackage().getName());
                }
                packageList.addAll(Arrays.asList(annotation.scanBasePackages()));
            }
        }
        return verifyPackageNameValidityAndSort(packageContextResolver(packageList), new LinkedList<>());
    }

    private List<String> packageContextResolver(List<String> packageList) {
        return packageList.stream()
                .filter(item -> packagePattern.matcher(item).find())
                .distinct()
                .sorted(Comparator.comparingInt(o -> (o.replace(".", "").length() - o.length())))
                .collect(Collectors.toList());
    }

    private List<String> verifyPackageNameValidityAndSort(List<String> packageList, List<String> pack) {
        for (String packageName : packageList) {
            String replace = packageName.replace(".", "/");
            URL resource = Thread.currentThread().getContextClassLoader().getResource(replace);
            if (!ObjectUtils.isEmpty(resource) && !StringUtils.isEmpty(packageName)) {
                pack.add(packageName);
            }
        }
        Map<String, List<String>> packageContent = new HashMap<>(8);
        for (String item : pack) {
            int idx = item.indexOf('.');
            String key = idx != -1 ? item.substring(0, idx) : item;
            List<String> list = packageContent.get(key);
            if (ObjectUtils.isEmpty(list)) {
                list = new LinkedList<>();
                list.add(item);
                packageContent.put(key, list);
            } else {
                List<String> collect = list.stream().filter((it) -> !it.startsWith(item)).collect(Collectors.toList());
                list.clear();
                list.addAll(collect);
                list.add(item);
            }
        }
        List<String> result = new LinkedList<>();
        for (Map.Entry<String, List<String>> stringListEntry : packageContent.entrySet()) {
            result.addAll(stringListEntry.getValue());
        }
        return result;
    }

    private <A extends Annotation> A getAnnotationFromProxyTarget(Class<?> proxy, @Nullable Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(proxy, annotationType);
    }

    @Override
    public void run(String... args) {
        List<ControllerAdviceBean> annotatedBeans = ControllerAdviceBean.findAnnotatedBeans(Objects.requireNonNull(getApplicationContext()));
        for (ControllerAdviceBean annotatedBean : annotatedBeans) {
            System.out.println(Arrays.toString(Objects.requireNonNull(annotatedBean.getBeanType()).getAnnotation(RestControllerAdvice.class).basePackages()));
        }
    }
}
