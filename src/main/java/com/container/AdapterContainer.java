package com.container;

import com.studentAdmin.api.apiAnnotation.AdapterDesc;
import com.studentAdmin.api.base.IAdapter;

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
public class AdapterContainer {

    private Map<String, IAdapter> adapterMap = new HashMap<String, IAdapter>();

    AdapterContainer() {
        Set<IAdapter> adapters = ContainerScanner.scanAdapter();
        for (IAdapter adapter : adapters) {
            AdapterDesc desc = adapter.getClass().getAnnotation(AdapterDesc.class);
            String id = desc.id();
            adapterMap.put(id,adapter);
        }
    }
}
