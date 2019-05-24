package com.studentAdmin.service;

import com.studentAdmin.domain.ClickLog;
import com.studentAdmin.domain.common.CommonException;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-22  20:31
 * @version: 1.0
 */
public interface CLickLogService {

    void insertLogService(long userId ,long articleId) throws CommonException;

    ClickLog checkUserLog(long userId,long articleId)  ;

}
