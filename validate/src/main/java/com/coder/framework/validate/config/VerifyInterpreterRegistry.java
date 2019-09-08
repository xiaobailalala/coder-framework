package com.coder.framework.validate.config;


import com.coder.framework.validate.annotation.EnableVerify;
import com.coder.framework.validate.aspect.VerifyMethodArgumentResolver;
import com.coder.framework.validate.handle.GlobalExceptionHandle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
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
    private List<String> packageContainer = new LinkedList<>();

    public List<String> getPackageContainer() {
        return packageContainer;
    }

    @Bean
    public VerifyMethodArgumentResolver verifyMethodArgumentResolver() {
        return new VerifyMethodArgumentResolver();
    }

    @Bean
    public GlobalExceptionHandle globalExceptionHandle() {
        return new GlobalExceptionHandle();
    }

    @Override
    public void run(String... args) {
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
        packageContainer.addAll(verifyPackageNameValidityAndSort(packageContextResolver(packageList), new LinkedList<>()));
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

}
