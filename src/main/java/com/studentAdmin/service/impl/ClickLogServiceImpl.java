package com.studentAdmin.service.impl;

import com.studentAdmin.dao.mapper.ClickLogMapper;
import com.studentAdmin.domain.ClickLog;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.CLickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-22  20:37
 * @version: 1.0
 */
@Service
public class ClickLogServiceImpl implements CLickLogService {

    @Autowired
    ClickLogMapper clickLogMapper;
    @Override
    public void insertLogService(long userId, long articleId) throws CommonException {
        if(this.checkUserLog(userId,articleId)!=null){
            throw new CommonException(Common.getCode_19(),Common.getMsg_19());
        }
        else{
            clickLogMapper.insertClickLog(userId,articleId);
        }
    }
    @Override
    public ClickLog checkUserLog(long userId, long articleId)  {
       return  clickLogMapper.checkHasRead(userId,articleId);
    }
}
