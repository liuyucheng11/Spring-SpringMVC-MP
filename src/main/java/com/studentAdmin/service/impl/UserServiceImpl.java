package com.studentAdmin.service.impl;

import com.common.QueryParam;
import com.common.Result;
import com.common.ResultPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.studentAdmin.dao.mapper.ArticleCollectMapper;
import com.studentAdmin.dao.mapper.ArticleMapper;
import com.studentAdmin.dao.mapper.UserAvatarMapper;
import com.studentAdmin.dao.mapper.UserMapper;
import com.studentAdmin.domain.*;
import com.studentAdmin.domain.VOs.ArticleVO;
import com.studentAdmin.domain.VOs.UserVO;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.UserService;
import com.studentAdmin.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:liu.yucheng
 * @Data:2019-2-28 16:14
 * @version:1.0
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleMapper articleMapper;



    @Autowired
    UserAvatarMapper userAvatarMapper;

    @Autowired
    ArticleCollectMapper articleCollectMapper;

    @Override
    //登录校验
    public Map userLogin(String userName, String password) {
        Map<String, Object> loginResult = new HashMap<>();
        boolean exist;
        try {
            User user = userMapper.doUserLogin(userName);
            if (user == null) {
                exist = false;
                loginResult.put("isExist", exist);
            } else if (!user.getUserPassword().equals(password)) {
                exist = true;
                loginResult.put("isExist", exist);
            } else {
                //密码符合校验规则
                loginResult.put("user", user);
                exist = true;
                loginResult.put("isExist", exist);
            }
            return loginResult;
        } catch (Exception e) {
            loginResult.put("hasExcept", true);

        } finally {
            return loginResult;
        }
    }

    /**
     * 用户注册事件
     *
     * @param user
     */
    @Override
    public void doRegister(@RequestBody User user) {
        userMapper.insert(user);
    }

    /**
     * 根据用户名全名查找
     *
     * @param userName
     * @return
     */
    @Override
    public User findByUserName(String userName) {
        return userMapper.findUserByName(userName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result readArticle(Long articleId) throws CommonException, IOException {
        if (articleId == null) {
            throw new CommonException("传入Id为空!");
        }
        Result result = new Result();
        Article article = new Article();
        Long collectNum;
        Long clickNum;
        try {
            article = articleMapper.searchArticleById(articleId);
            collectNum = articleMapper.queryCollectNum(articleId);
            clickNum = articleMapper.queryClickNum(articleId);
        } catch (Exception e) {
            return result.error(Common.getCode_9(), Common.getMsg_9());
        }
        result.put("article", article);
        result.put("collectNum", collectNum);
        result.put("clickNum", clickNum);
        String url = article.getHomeUrl();
        StringBuffer articleContent = new StringBuffer();
        File file = new File(url);
        String encoding = "gbk";
        //文件是否存在
        if (file.isFile() && file.exists()) {
            InputStreamReader read = null;//考虑到编码格式
            try {
                read = new InputStreamReader(
                        new FileInputStream(file), encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return result.error(Common.getCode_9(), Common.getMsg_9());
            }
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                System.out.println(lineTxt);
                articleContent.append(lineTxt);
            }
            read.close();
        }
        result.put("articleContent", articleContent);
        result.put("article", article);
        return result;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadAvatar(MultipartFile file, String url) throws Exception {
        User user = (User) SessionUtil.getSessionAttribute("user");
        if (user == null) {
            throw new CommonException(Common.getMsg_10());
        }
        try {
            File avatarFile = new File(url);
            if (avatarFile.exists()) {
                //删除已有头像文件
                avatarFile.delete();
            }
            avatarFile.createNewFile();
            //写入文件
            file.transferTo(avatarFile);
        } catch (Exception e) {
            throw new CommonException("文件流错误!");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getUserId());
        //删除表中存在
        try {
            userAvatarMapper.deleteByMap(map);
            UserAvatar userAvatar = new UserAvatar();
            userAvatar.setAvatarUrl(url);
            userAvatar.setUserId(user.getUserId());
            userAvatar.setCreateDate(new Date(System.currentTimeMillis()));
            userAvatar.setModifyDate(new Date(System.currentTimeMillis()));
            userAvatarMapper.insert(userAvatar);
        } catch (Exception e) {
            throw new CommonException("操作异常");
        }


    }

    @Override
    public List<UserAvatar> getUserAvatar(Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        return userAvatarMapper.selectByMap(map);
    }

    @Override
    public Result collectArticle(Long articleId, Long userId) throws CommonException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("article_id", articleId);
        param.put("user_id", userId);
        List<ArticleCollect> articleCollects = articleCollectMapper.selectByMap(param);
        if (articleCollects.size() == 0) {
            try {
                userMapper.collectArticle(articleId, userId);
                return Result.ok();

            } catch (Exception e) {
                throw new CommonException("收藏异常!");
            }
        } else {
            return Result.error(Common.getCode_13(), Common.getMsg_13());
        }
    }

    @Override
    public ResultPage getOtherUser(QueryParam param) {
        Page<?> page = PageHelper.startPage(param.getPage(), param.getLimit());
        List<UserVO> userVOS = userMapper.getOtherUser(param);
        return new ResultPage((int) page.getTotal(), param.getLimit(), page.getPages(), param.getPage(), userVOS);
    }

    @Override
    public ResultPage getWorksByUserId(QueryParam param) {
        Page<?> page = PageHelper.startPage(param.getPage(),param.getLimit());
        List<ArticleVO> articleVOS = articleMapper.getArticlesByUserId(param);
        return  new ResultPage((int) page.getTotal(),param.getLimit(),page.getPages(),param.getPage(),articleVOS);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result followUser(Long userId, Long followedId) {
        UserFollow userFollow = userMapper.checkIsFriend(userId,followedId);
        if(userFollow != null){
            return  Result.error(Common.getCode_14(),Common.getMsg_14());
        }
        try{
            userMapper.followUser(userId,followedId);
        }catch (Exception e){
            return Result.error();
        }
        return  Result.ok();
    }
}


