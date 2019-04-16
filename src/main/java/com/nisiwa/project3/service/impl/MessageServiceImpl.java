package com.nisiwa.project3.service.impl;

import com.nisiwa.project3.VO.MessageVo;
import com.nisiwa.project3.bean.Message;
import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.mapper.MessageMapper;
import com.nisiwa.project3.service.MessageService;
import com.nisiwa.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-14 19:46
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    UserService userService;

    @Override
    public boolean addMessage(Message message) {
        return messageMapper.insert(message) == 1;
    }

    @Override
    public List<Message> queryAllMsgByConversationId(String conversationId) {
        return messageMapper.selectByConversationId(conversationId);
    }

    @Override
    public List<MessageVo> queryMsgVoMsgByConversationId(String conversationId) {
        List<MessageVo> messageVoList = new ArrayList<>();
        List<Message> messageList = queryAllMsgByConversationId(conversationId);
        for (Message message : messageList) {
            Integer fromId = message.getFromId();
            User user = userService.selectUserById(fromId);
            String headUrl = user.getHeadUrl();
            MessageVo messageVo = new MessageVo();
            messageVo.setHeadUrl(headUrl);
            messageVo.setUserId(fromId);
            messageVo.setMessage(message);
            messageVoList.add(messageVo);
        }
        return messageVoList;
    }
}
