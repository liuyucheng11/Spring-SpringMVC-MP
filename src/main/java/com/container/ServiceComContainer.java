package com.container;

import com.studentAdmin.api.apiAnnotation.ServiceCom;
import com.studentAdmin.api.apiConfig.APIConfig;
import com.studentAdmin.api.base.IComponent;
import com.studentAdmin.api.base.IServiceCom;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 服务类容器管理
 *
 * @author: liu.yucheng
 * @Date: 2019-7-6  11:21
 * @version: 1.0
 */
public class ServiceComContainer extends APIHandler {
    private Map<String, IServiceCom> serviceComMap = new HashMap<>();

    public ServiceComContainer() {
        Set<IServiceCom> serviceComs = ContainerScanner.scanServiceCom();
        for (IServiceCom iServiceCom : serviceComs){
            ServiceCom desc = iServiceCom.getClass().getAnnotation(ServiceCom.class);
            String id = desc.id();
            serviceComMap.put(id,iServiceCom);
        }
    }

    @Override
    protected IServiceCom component(String id){
        IServiceCom component = serviceComMap.get(id);
        if(component == null){
            throw new RuntimeException();
        }
        return component;
    }

    @Override
    protected void postHandle() {
        RequestContext.setDTO(getSupportedCom().invoke());
    }

    @Override
    protected IComponent getSupportedCom() {
        APIConfig apiConfig = RequestContext.getAPIConfig();
        String id= apiConfig.getServiceCom().id();
        return component(id);

    }
}
