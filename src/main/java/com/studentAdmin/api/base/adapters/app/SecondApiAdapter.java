package com.studentAdmin.api.base.adapters.app;

import com.container.RequestContext;
import com.studentAdmin.api.apiAnnotation.AdapterDesc;
import com.studentAdmin.api.base.adapters.AbstractAdapter;

/**
 * @author: liu.yucheng
 * @Date: 2019-7-6  11:13
 * @version: 1.0
 */
@AdapterDesc(id="/api/service/secondApi.do" , serviceId = "secondServiceCom")
public class SecondApiAdapter extends AbstractAdapter {
    @Override
    public Object invoke() {
        return RequestContext.getDTO();
    }
}
