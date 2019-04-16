package com.nisiwa.project3.mapper;

import com.nisiwa.project3.bean.Conversation;
import java.util.List;

public interface ConversationMapper {
    int deleteByPrimaryKey(String conversationId);

    int insert(Conversation record);

    Conversation selectByPrimaryKey(String conversationId);

    List<Conversation> selectAll();

    int updateByPrimaryKey(Conversation record);

    List<Conversation> selectAllConversationByUserId(Integer id);
}