package com.studentAdmin.service;

import com.common.QueryParam;
import com.common.Result;
import com.common.ResultPage;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.UserAvatar;
import com.studentAdmin.domain.VOs.UserVO;
import com.studentAdmin.domain.common.CommonException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author:liu.yucheng
 * @Data:2019-2-28 16:12
 * @version:1.0
 */
public interface UserService {
    /**
     * 用户登录事件
     * @param userName
     * @param password
     */
    public Map  userLogin(String userName, String password);

    /**
     * 用户注册事件
     */
    public void doRegister(User user);

    /**
     * 根据详细用户名查找
     * @param userName
     * @return
     */
    public User findByUserName(String userName);

    /**
     * 阅读文章
     * @param articleId
     * @return
     * @throws CommonException
     * @throws IOException
     */
    public Result readArticle(Long articleId) throws CommonException, IOException;

    /**
     * 头像管理
     * @param file
     * @param url
     * @throws Exception
     */
     void uploadAvatar(MultipartFile file ,String url) throws Exception;


    /**
     * 获取头像
     * @return
     */
     List<UserAvatar> getUserAvatar(Long userId);

    /**
     * 收藏文章
     */
      Result collectArticle(Long articleId, Long userId) throws CommonException;

    /**
     * 获取用户
     * @param param
     * @return
     */

      ResultPage getOtherUser(QueryParam param);

    /**
     * 根据userId获取相应的文章作品
     * @param param
     * @return
     */
      ResultPage getWorksByUserId(QueryParam param);

    /**
     * 关注用户
     * @param userId
     * @param followedId
     * @return
     */
      Result followUser(Long userId, Long followedId);


}
