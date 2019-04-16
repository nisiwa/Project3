package com.nisiwa.project3.controller;

import com.nisiwa.project3.VO.ConversationVo;
import com.nisiwa.project3.VO.MessageVo;
import com.nisiwa.project3.bean.Conversation;
import com.nisiwa.project3.bean.Message;
import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.service.ConversationService;
import com.nisiwa.project3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-14 20:49
 */
@Controller
@RequestMapping("msg")
public class MessageController {
    @Autowired
    ConversationService conversationService;

    @Autowired
    MessageService messageService;

    @RequestMapping("list")
    public String conversationList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<ConversationVo> conversationVoList = conversationService.selectConversationListbyUserId(user.getId());
       /* ArrayList<ConversationVo> conversationVoList = new ArrayList<>();
        ConversationVo conversationVo = new ConversationVo();
        conversationVo.setUser(new User());
        conversationVo.setConversation(new Conversation());
        conversationVoList.add(conversationVo);*/
        model.addAttribute("conversations", conversationVoList);
        return "letter";
    }

    @RequestMapping("detail")
    public String msgList(String conversationId, Model model) {
        /*List<Message> messageList = messageService.queryAllMsgByConversationId(conversationId);
        model.addAttribute("messages", messageList);*/
        List<MessageVo> messageVos = messageService.queryMsgVoMsgByConversationId(conversationId);
        model.addAttribute("messages", messageVos);
        return "letterDetail";
    }
}
