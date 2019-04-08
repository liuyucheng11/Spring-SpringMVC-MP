package com.studentAdmin.service.impl;

import com.common.Result;
import com.studentAdmin.dao.mapper.ArticleMapper;
import com.studentAdmin.dao.mapper.UserMapper;
import com.studentAdmin.domain.Article;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.UserVO;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
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
        try {
            article = articleMapper.selectById(articleId);
        } catch (Exception e) {
            return result.error(Common.getCode_9(),Common.getMsg_9());
        }
        result.put("article",article);
        String url = article.getHomeUrl();
        StringBuffer articleContent = new StringBuffer();
        File file = new File(url);
        String encoding = "utf-8";
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
                return result.error(Common.getCode_9(),Common.getMsg_9());
            }
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                System.out.println(lineTxt);
                articleContent.append(lineTxt);
            }
            read.close();
        }
       result.put("articleContent",articleContent);
        return  result;

    }


}


