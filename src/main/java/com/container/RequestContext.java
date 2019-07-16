package com.container;

import com.studentAdmin.api.apiAnnotation.AdapterDesc;
import com.studentAdmin.api.apiAnnotation.ServiceCom;
import com.studentAdmin.api.apiConfig.APIConfig;
import com.studentAdmin.api.base.IAdapter;
import com.studentAdmin.api.base.IServiceCom;

/**
 * @desc: api多线程执行上下文
 * @author: liu.yucheng
 * @Date: 2019-7-12  17:36
 * @version: 1.0
 */
public class RequestContext {

    private static ThreadLocal<APIConfig> apiConfigThread = new ThreadLocal<>();

    private static ThreadLocal<Object> DTO = new ThreadLocal<>();

    public static APIConfig getAPIConfig() {
        APIConfig apiConfig = apiConfigThread.get();
        if (apiConfig == null) {
            return null;
        } else {
            return apiConfig;
        }
    }

    public static void setAPIConfig(APIConfig apiConfig) {
        apiConfigThread.set(apiConfig);
    }

    public static void setDTO(Object o) {
        DTO.set(o);
    }

    public static Object getDTO() {
        return DTO.get();
    }

    public  static void initAPIConfig(String adapterId) {
        APIConfig apiConfig = new APIConfig();
        IAdapter iAdapter = ContainerManager.getAdapterContainer().component(adapterId);
        AdapterDesc adapterDesc = iAdapter.getClass().getAnnotation(AdapterDesc.class);
        String serviceId = adapterDesc.serviceId();
        IServiceCom iserviceCom = ContainerManager.getServiceComContainer().component(serviceId);
        ServiceCom serviceCom = iserviceCom.getClass().getAnnotation(ServiceCom.class);
        apiConfig.setAdapterDesc(adapterDesc);
        apiConfig.setServiceCom(serviceCom);
        //为当前request线程配置api配置
        setAPIConfig(apiConfig);
    }


}
