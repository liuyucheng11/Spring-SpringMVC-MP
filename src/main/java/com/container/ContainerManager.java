package com.container;

/**
 * 管理容器单例实现
 *
 * @author: liu.yucheng
 * @Date: 2019-7-6  11:19
 * @version: 1.0
 */
public class ContainerManager {

    private static volatile AdapterContainer adapterContainer;

    private static volatile ServiceComContainer serviceComContainer;

    /**
     * 懒汉模式实现创建单例
     */
    public static void initAdapterContainer() {
        if (adapterContainer == null) {
            synchronized (AdapterContainer.class) {
                if (adapterContainer == null) adapterContainer = new AdapterContainer();
            }
        }
    }

    public static void initServiceComContainer() {
        if (serviceComContainer == null) {
            synchronized (ServiceComContainer.class) {
                if (serviceComContainer == null) {
                    serviceComContainer = new ServiceComContainer();
                }
            }
        }
    }

    public static AdapterContainer getAdapterContainer() {
        if (adapterContainer == null) {
            initAdapterContainer();
        }
        return adapterContainer;
    }

    public static ServiceComContainer getServiceComContainer() {
        if (serviceComContainer == null) {
            initServiceComContainer();
        }
        return serviceComContainer;
    }

    public static void init() {
        adapterContainer.prepend(serviceComContainer);
    }
}
