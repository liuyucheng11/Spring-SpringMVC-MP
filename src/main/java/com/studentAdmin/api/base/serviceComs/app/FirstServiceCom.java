package com.studentAdmin.api.base.serviceComs.app;

import com.studentAdmin.api.apiAnnotation.ServiceCom;
import com.studentAdmin.api.base.serviceComs.AbstractServiceCom;
import org.springframework.stereotype.Service;

/**
 * @author: liu.yucheng
 * @Date: 2019-7-6  11:48
 * @version: 1.0
 */
@ServiceCom(id="FirstServiceCom")
public class FirstServiceCom extends AbstractServiceCom {
    @Override
    public Object invoke() {
        return null;
    }
}
