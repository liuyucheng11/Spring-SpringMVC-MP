package com.studentAdmin.controller;

import com.common.Result;
import com.studentAdmin.domain.Dto.LoginDto;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.UserVO;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.service.UserService;
import com.studentAdmin.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:"liu.yucheng",
 * @Data:$Date
 */
@RequestMapping("/login")
@Controller("loginController")
public class LoginController {
    //相应login请求
    @Autowired
    UserService userService;

    @RequestMapping("/login.do_")
    @ResponseBody
    public Map doLogin( @RequestBody LoginDto loginDto) {
        Map<String, Object> loginResult = new HashMap<>();
        Map qryResult = userService.userLogin(loginDto.getUserName(), loginDto.getPassword());
        //防止报错
        if (qryResult.get("isExist") !=null && (boolean)qryResult.get("isExist")) {
            //校验成功
            if ((boolean) qryResult.get("isExist") && qryResult.get("user") != null) {
                User user = (User) qryResult.get("user");
                //防止密码泄露
                user.setUserPassword("**********");
                loginResult.put("exist", true);
                loginResult.put("user", user);
                //将 user存入session中
                SessionUtil.setSessionAttribute("user",user);
            } else{
                loginResult.put("exist", true);
                loginResult.put("passwordError",true);
            }
        } else {
            loginResult.put("exist", false);
        }
        if(qryResult.get("hasExcept")!=null && (boolean)qryResult.get("hasExcept")){
            loginResult.put("serverError", true);
        }
        return loginResult;
    }

    /**
     * 登录校验
     * @param userName
     * @return
     */
    @RequestMapping("/checkName.do_")
    @ResponseBody
    public boolean  checkNameUnique( String userName){
        if(userService.findByUserName(userName)!=null){
            return true;
        }else return false;
    }

    /**
     * 注册事件
     * @param user
     * @return
     */
    @RequestMapping("/doRegister.do_")
    @ResponseBody
    public Result doRegister(@RequestBody User user){
        try{
            userService.doRegister(user);
           return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
         return   Result.error(Common.getCode_8(),Common.getMsg_8());
        }
    }

}
