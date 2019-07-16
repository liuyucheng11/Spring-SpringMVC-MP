package com.studentAdmin.api.base.serviceComs.app;

import com.studentAdmin.api.apiAnnotation.ServiceCom;
import com.studentAdmin.api.base.serviceComs.AbstractServiceCom;

/**
 * @author: liu.yucheng
 * @Date: 2019-7-6  13:32
 * @version: 1.0
 */
@ServiceCom(id="SecondServiceCom")
public class SecondServiceCom extends AbstractServiceCom {
    @Override
    public Object invoke() {
        return "SecondServiceInvoke!";
    }
}
