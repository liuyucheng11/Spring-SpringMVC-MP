package com.studentAdmin.service;

import com.studentAdmin.domain.ThumbComment;
import com.studentAdmin.domain.common.CommonException;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-10  19:34
 * @version: 1.0
 */
public interface ThumbScoreService {
    void insertThumbScore(long scoreId,long userId) throws CommonException;


    void deleteThumb(long id);



}
