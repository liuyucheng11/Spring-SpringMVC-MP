package com.studentAdmin.service.impl;

import com.studentAdmin.dao.mapper.ThumbScoreMapper;
import com.studentAdmin.domain.ThumbComment;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.ThumbScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理评论点赞
 * @author: liu.yucheng
 * @Date: 2019-5-10  19:34
 * @version: 1.0
 */
@Service
public class ThumbScoreServiceImpl implements ThumbScoreService {

    @Autowired
    ThumbScoreMapper thumbScoreMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertThumbScore(long scoreId, long userId) throws CommonException {
        ThumbComment thumbComment = thumbScoreMapper.checkIsThumbed(scoreId, userId);
        if (thumbComment != null) {
            throw new CommonException(Common.getCode_18(), Common.getMsg_18());
        }
        thumbScoreMapper.insertThumbScore(scoreId, userId);
    }

    @Override
    public void deleteThumb(long id) {
          thumbScoreMapper.deleteThumb(id);
    }
}
