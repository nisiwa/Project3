package com.nisiwa.project3.service;

import com.nisiwa.project3.VO.MessageVo;
import com.nisiwa.project3.bean.Message;

import java.util.List;

public interface MessageService {
    boolean addMessage(Message message);

    List<Message> queryAllMsgByConversationId(String conversationId);

    List<MessageVo> queryMsgVoMsgByConversationId(String conversationId);
}
