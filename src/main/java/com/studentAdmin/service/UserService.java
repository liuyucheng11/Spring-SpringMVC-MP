package com.studentAdmin.service;

import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.UserVO;

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
}
