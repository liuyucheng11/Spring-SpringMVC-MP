package com.studentAdmin.service.impl;

import com.studentAdmin.dao.mapper.UserMapper;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.UserVO;
import com.studentAdmin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    //登录校验
    public Map userLogin(String userName, String password) {
        Map<String, Object> loginResult = new HashMap<>();
        try {
            User user = userMapper.doUserLogin(userName);
            boolean exist;

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
            return  loginResult;
        }
        catch (Exception e) {
              loginResult.put("hasExcept",true);
              return  loginResult;
        }
    }

    @Override
    public void doRegister() {

    }
}


