package com.nisiwa.project3.VO;

import com.nisiwa.project3.bean.Message;

/**
 * @author: nisiwa
 * @date: 2019-04-14 21:08
 */
public class MessageVo {
    Message message;
    int UserId;
    String headUrl;

    public MessageVo() {
    }

    public MessageVo(Message message, int userId, String headUrl) {
        this.message = message;
        UserId = userId;
        this.headUrl = headUrl;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "message=" + message +
                ", UserId=" + UserId +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}
