package com.studentAdmin.controller;
import com.common.Result;
import com.studentAdmin.dao.mapper.ChatMessageMapper;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.ChatMessageVO;
import com.studentAdmin.service.ChatMessageService;
import com.studentAdmin.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 聊天控制器
 *
 * @author: liu.yucheng
 * @Date: 2019-5-5  16:56
 * @version: 1.0
 */
@Controller
public class ChatController {

    @Autowired
    ChatMessageService chatMessageService;

    @RequestMapping("user/getChatMessage.do")
    @ResponseBody
    public Result getChatMessage(@RequestParam("receiverId") long receiverId) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        List<ChatMessageVO> chatMessageVOS = chatMessageService.queryChatMessage(user.getUserId(),receiverId);
       return  Result.ok().put("list",chatMessageVOS);
    }

    @RequestMapping("user/sendChatMessage.do")
    @ResponseBody
    public Result sendChatMessage(@RequestParam("receiverId") long receiverId,@RequestParam("message") String message){
        User user = (User) SessionUtil.getSessionAttribute("user");
        try {
            chatMessageService.sendChatMessage(user.getUserId(), receiverId, message);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

}
