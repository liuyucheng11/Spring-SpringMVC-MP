package com.studentAdmin.api.apiConfig;

import com.studentAdmin.api.apiAnnotation.AdapterDesc;
import com.studentAdmin.api.apiAnnotation.ServiceCom;

/**
 * @description: api配置上下文
 * @author: liu.yucheng
 * @Date: 2019-7-12  17:39
 * @version: 1.0
 */
public class APIConfig {
    private AdapterDesc adapterDesc;
    private ServiceCom serviceCom;

    public AdapterDesc getAdapterDesc() {
        return adapterDesc;
    }

    public void setAdapterDesc(AdapterDesc adapterDesc) {
        this.adapterDesc = adapterDesc;
    }

    public ServiceCom getServiceCom() {
        return serviceCom;
    }

    public void setServiceCom(ServiceCom serviceCom) {
        this.serviceCom = serviceCom;
    }
}
