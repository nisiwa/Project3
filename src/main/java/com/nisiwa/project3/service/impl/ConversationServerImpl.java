package com.nisiwa.project3.service.impl;

import com.nisiwa.project3.VO.ConversationVo;
import com.nisiwa.project3.bean.Conversation;
import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.mapper.ConversationMapper;
import com.nisiwa.project3.service.ConversationService;
import com.nisiwa.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-14 19:32
 */
@Service
public class ConversationServerImpl implements ConversationService {
    @Autowired
    ConversationMapper conversationMapper;

    @Autowired
    UserService userService;

    @Override
    public Conversation selectConversationById(String id) {
        return conversationMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean addConversation(Conversation conversation) {
        return conversationMapper.insert(conversation) == 1;
    }

    @Override
    public boolean updateConversation(Conversation conversation) {
        return conversationMapper.updateByPrimaryKey(conversation) == 1;
    }

    @Override
    public List<ConversationVo> selectConversationListbyUserId(Integer id) {
        List<ConversationVo> conversationVoList = new ArrayList<>();
        List<Conversation> conversationList = conversationMapper.selectAllConversationByUserId(id);
        for (Conversation conversation : conversationList) {
            ConversationVo conversationVo = new ConversationVo();
            conversationVo.setConversation(conversation);
            // 这个user都设置为对方
            Integer toId = conversation.getToId();
            Integer fromId = conversation.getFromId();
            int selectId = 0;
            if (id.equals(toId)) {
                selectId = fromId;
            } else {
                selectId = toId;
            }
            User user = userService.selectUserById(selectId);
            conversationVo.setUser(user);
            conversationVoList.add(conversationVo);
        }
        return conversationVoList;
    }
}
