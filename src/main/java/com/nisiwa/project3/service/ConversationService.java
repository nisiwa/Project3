package com.nisiwa.project3.service;

import com.nisiwa.project3.VO.ConversationVo;
import com.nisiwa.project3.bean.Conversation;

import java.util.List;

public interface ConversationService {
    Conversation selectConversationById(String id);

    boolean addConversation(Conversation conversation);

    boolean updateConversation(Conversation conversation);

    List<ConversationVo> selectConversationListbyUserId(Integer id);
}
