package com.studentAdmin.api.base.adapters.app;


import com.studentAdmin.api.apiAnnotation.AdapterDesc;
import com.studentAdmin.api.base.adapters.AbstractAdapter;

/**
 * @author: liu.yucheng
 * @Date: 2019-7-6  10:55
 * @version: 1.0
 *
 *
 *
 *
 */
@AdapterDesc(id="/api/service/firstApi.do" , serviceId = "FirstServiceCom")
public class FirstApiAdapter extends AbstractAdapter {
    @Override
    public Object invoke() {
        return null;
    }
}
