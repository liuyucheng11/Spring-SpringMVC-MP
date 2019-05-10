package com.studentAdmin.service.impl;

import com.common.Result;
import com.studentAdmin.dao.mapper.ThumbsMapper;
import com.studentAdmin.domain.Thumbs;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.service.ThumbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  15:09
 * @version: 1.0
 */
@Service
public class ThumbsServiceImpl implements ThumbsService {

    @Autowired
    ThumbsMapper thumbsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertThumbs(long userId, long thumbsId) {
        Thumbs thumbs = thumbsMapper.checkThumbs(userId, thumbsId);
        if (thumbs == null) {
            try {
                thumbsMapper.insertThumbs(userId, thumbsId);
                return Result.ok();
            } catch (Exception e) {
                return Result.error();
            }
        } else {
            return Result.error(Common.getCode_16(), Common.getMsg_16());
        }

    }
}
