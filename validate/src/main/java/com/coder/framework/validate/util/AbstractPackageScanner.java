package com.coder.framework.validate.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
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
 * Build File @date: 2019/9/5 13:14
 * @version 1.0
 * @description
 */
public abstract class AbstractPackageScanner {

    protected AbstractPackageScanner() { }

    /**
     * 回调方法
     * @param klass 将符合的类对象进行回调
     */
    public abstract void dealClass(Class<?> klass);

    public void packageScanner(Class<?> klass) {
        packageScanner(klass.getPackage().getName());
    }

    public void packageScanner(String packageName) {
        String packagePath = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String fileType = "jar";
                if (url.getProtocol().equals(fileType)) {
                    scanPackage(url);
                } else {
                    File file = new File(url.toURI());
                    if (!file.exists()) {
                        continue;
                    }
                    scanPackage(packageName, file);
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void scanPackage(URL url) throws IOException {
        JarURLConnection jarUrlConnection =  (JarURLConnection) url.openConnection();
        JarFile jarFile = jarUrlConnection.getJarFile();
        Enumeration<JarEntry> jarEntries = jarFile.entries();

        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarName = jarEntry.getName();
            if (jarEntry.isDirectory() || !jarName.endsWith(".class")) {
                continue;
            }
            String className = jarName.replace(".class", "").replaceAll("/", ".");
            clazzTypeResolver(className);
        }
    }

    private void clazzTypeResolver(String className) {
        try {
            Class<?> klass = Class.forName(className);
            if (klass.isAnnotation()
                    ||klass.isInterface()
                    ||klass.isEnum()
                    ||klass.isPrimitive()) {
                return;
            }
            dealClass(klass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void scanPackage(String packageName, File currentFile) {
        File[] files = currentFile.listFiles((pathname) -> {
            if (currentFile.isDirectory()) {
                return true;
            }
            return pathname.getName().endsWith(".class");
        });
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                scanPackage(packageName + "." + file.getName(), file);
            } else {
                String fileName = file.getName().replace(".class", "");
                String className = packageName + "." + fileName;
                clazzTypeResolver(className);
            }
        }

    }
}
