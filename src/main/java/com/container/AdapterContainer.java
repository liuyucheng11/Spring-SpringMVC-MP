package com.container;

import com.studentAdmin.api.apiAnnotation.AdapterDesc;
import com.studentAdmin.api.apiConfig.APIConfig;
import com.studentAdmin.api.base.IAdapter;
import com.studentAdmin.api.base.IComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 适配器容器
 *
 * @author: liu.yucheng
 * @Date: 2019-7-6  11:20
 * @version: 1.0
 */
public class AdapterContainer extends APIHandler {

    private Map<String, IAdapter> adapterMap = new HashMap<String, IAdapter>();

    AdapterContainer() {
        Set<IAdapter> adapters = ContainerScanner.scanAdapter();
        for (IAdapter adapter : adapters) {
            AdapterDesc desc = adapter.getClass().getAnnotation(AdapterDesc.class);
            String id = desc.id();
            adapterMap.put(id, adapter);
        }
    }

    /**
     * 获取API实例
     *
     * @param id
     * @return
     */
    public IAdapter component(String id) {
        IAdapter adapter = adapterMap.get(id);
        if (adapter == null) {
            throw new RuntimeException();
        }
        return adapter;
    }

    public Map<String, IAdapter> getAdapterMap() {
        return adapterMap;
    }

    @Override
    protected IComponent getSupportedCom() {
        APIConfig apiConfig = RequestContext.getAPIConfig();
        String id = apiConfig.getAdapterDesc().id();
        return component(id);
    }

    @Override
    protected void postHandle() {
          RequestContext.setDTO(getSupportedCom().invoke());
    }
}
