package com.container;

import com.studentAdmin.api.apiAnnotation.AdapterDesc;
import com.studentAdmin.api.apiAnnotation.ServiceCom;
import com.studentAdmin.api.base.IAdapter;
import com.studentAdmin.api.base.IServiceCom;
import org.apache.commons.lang.ClassUtils;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 容器扫描类
 *
 * @author: liu.yucheng
 * @Date: 2019-7-6  11:23
 * @version: 1.0
 */
public class ContainerScanner {
    /**
     * class 缓存
     */
    private static Set<Class<?>> classFiles = new HashSet<>();

    /**
     * apiAdapters扫描路径
     */
    @Deprecated
    private static final String ADAPTER_SCAN_DIR = "com.studentAdmin.api.base.adapters.app";

    /**
     * serviceCom扫描路径
     */
    @Deprecated
    private static final String SERVICE_SCAN_DIR = "com.studentAdmin.api.base.serviceComs.app";

    /**
     * API组件目录用于扫描
     */
    private static final String SCAN_DIR = "com.studentAdmin.api.base";


    protected static Set<Class<?>> scan(Class<?> classObj) {
        try {
            if (classFiles.isEmpty()) {
                initFiles();
            }
            Set<Class<?>> resultSet = new HashSet<Class<?>>();
            for (Class<?> clazz : classFiles) {
                List<Class<?>> interfaces = ClassUtils.getAllInterfaces(clazz);
                if (interfaces.contains(classObj)) {
                    resultSet.add(clazz);
                }
            }
            return resultSet;
        } catch (Exception e) {

        }
        return null;
    }

    private static void initFiles() throws ClassNotFoundException {
        synchronized (ContainerScanner.class) {
            if (!classFiles.isEmpty()) {
                return;
            }
            scanWithFile(SCAN_DIR);
        }
    }

    /**
     * 扫描目录下的文件
     *
     * @param rootDir
     * @throws ClassNotFoundException
     */
    private static void scanWithFile(String rootDir) throws ClassNotFoundException {
        String appPath = rootDir.replace(".", "/");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL appUrl = loader.getResource(appPath);
        initFilesLoop(new File(appUrl.getFile()), rootDir);
    }

    /**
     * 搜索迭代方法
     *
     * @param file
     * @param packageName
     * @throws ClassNotFoundException
     */
    private static void initFilesLoop(File file, String packageName) throws ClassNotFoundException {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory() || pathname.getName().endsWith(".class");
                }
            });
            if (subFiles == null) {
                return;
            }
            for (File subFile : subFiles) {
                String subPackageName = packageName;
                if (subFile.isDirectory()) {
                    subPackageName += "." + subFile.getName();
                }
                initFilesLoop(subFile, subPackageName);
            }
        } else {
            String className = file.getName().replace(".class", "");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            classFiles.add(classLoader.loadClass(packageName + "." + className));
        }
    }

    /**
     * 查找所有Adapter注解类
     *
     * @return
     */
    static Set<IAdapter> scanAdapter() {
        Set<IAdapter> adapters = new HashSet<>();
        Set<Class<?>> classSet = scan(IAdapter.class);
        if (classSet != null) {
            for (Class<?> clazz : classSet) {
                //查找注解
                AdapterDesc config = clazz.getAnnotation(AdapterDesc.class);
                if (config != null) {
                    try {
                        adapters.add(IAdapter.class.cast(clazz.newInstance()));

                    } catch (Exception e) {
                    }
                }
            }
        }
        return adapters;
    }

    /**
     * 查找ServiceCom
     * @return
     */
    static Set<IServiceCom> scanServiceCom() {
        Set<IServiceCom> serviceComs = new HashSet<>();
        Set<Class<?>> classSet = scan(IServiceCom.class);
        if (classSet != null) {
            for (Class<?> clazz : classSet) {
                //查找注解
                ServiceCom config = clazz.getAnnotation(ServiceCom.class);
                if (config != null) {
                    try {
                        serviceComs.add(IServiceCom.class.cast(clazz.newInstance()));

                    } catch (Exception e) {
                    }
                }
            }
        }
        return serviceComs;
    }



}
