package com.nisiwa.project3.mapper;

import com.nisiwa.project3.bean.Message;
import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    List<Message> selectByConversationId(String conversationId);
}