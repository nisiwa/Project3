package com.nisiwa.project3.VO;

import com.nisiwa.project3.bean.Conversation;
import com.nisiwa.project3.bean.User;

/**
 * @author: nisiwa
 * @date: 2019-04-14 16:43
 */
public class ConversationVo {
    Conversation conversation;
    User user;
    int unRead;

    public ConversationVo() {
    }

    public ConversationVo(Conversation conversation, User user, int unRead) {
        this.conversation = conversation;
        this.user = user;
        this.unRead = unRead;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUnRead() {
        return unRead;
    }

    public void setUnRead(int unRead) {
        this.unRead = unRead;
    }

    @Override
    public String toString() {
        return "ConversationVo{" +
                "conversation=" + conversation +
                ", user=" + user +
                ", unRead=" + unRead +
                '}';
    }
}
