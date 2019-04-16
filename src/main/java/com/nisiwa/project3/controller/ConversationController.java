package com.nisiwa.project3.controller;

import com.nisiwa.project3.VO.ConversationVo;
import com.nisiwa.project3.bean.Conversation;
import com.nisiwa.project3.bean.Message;
import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.service.ConversationService;
import com.nisiwa.project3.service.LikeService;
import com.nisiwa.project3.service.MessageService;
import com.nisiwa.project3.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-14 18:39
 */
@Controller
@RequestMapping("user")
public class ConversationController {
    @Autowired
    UserService userService;

    @Autowired
    ConversationService conversationService;

    @Autowired
    MessageService messageService;

    @RequestMapping("tosendmsg")
    public String toSendMsg() {
        return "sendmsg";
    }

    @RequestMapping("/msg/addMessage")
    @ResponseBody
    public HashMap addMessage(String toName, String content, HttpServletRequest request) {
        User fromUser = (User) request.getSession().getAttribute("user");
        Message message = new Message();
        HashMap<Object, Object> map = new HashMap<>();
        LoggerFactory.getLogger(this.getClass()).info("addMessage接收参数：" + toName + "-" + content);
        User toUser = userService.selectByName(toName);
        if (toUser == null) {
            map.put("code", "1");
            map.put("msg", "收信人不存在");
        } else {
            // 先看有没有存在会话，约定，其id规则为：小id_大id
            String conversationId;
            int fromUserId = fromUser.getId();
            int toUserId = toUser.getId();
            if (fromUserId < toUserId) {
                conversationId = fromUserId + "_" + toUserId;
            } else {
                conversationId = toUserId + "_" + fromUserId;
            }
            Conversation conversation = conversationService.selectConversationById(conversationId);
            message.setContent(content);
            message.setConversationId(conversationId);
            message.setCreatedDate(new Date());
            message.setFromId(fromUserId);
            message.setToId(toUserId);
            message.setHasRead(0);

            Conversation newConversation = new Conversation();
            newConversation.setFromId(fromUserId);
            newConversation.setToId(toUserId);
            newConversation.setMsgNum(1);
            newConversation.setContent(content);
            newConversation.setConversationId(conversationId);
            newConversation.setCreatedDate(new Date());

            // 需要新建会话
            if (conversation == null) {
                if (messageService.addMessage(message)) {
                    if (conversationService.addConversation(newConversation)) {
                        map.put("code", "0");
                    } else {
                        map.put("code", "1");
                        map.put("msg", "数据插入失败");
                    }
                }
            } else {
                // 已经存在回话，那就修改会话的数量字段
                conversation.setMsgNum(conversation.getMsgNum() + 1);
                if (conversationService.updateConversation(conversation) && messageService.addMessage(message)) {
                    map.put("code", "0");
                } else {
                    map.put("code", "1");
                    map.put("msg", "数据插入失败");
                }
            }
        }
        return map;
    }
}
