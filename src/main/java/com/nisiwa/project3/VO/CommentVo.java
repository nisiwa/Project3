package com.nisiwa.project3.VO;

import com.nisiwa.project3.bean.Comment;
import com.nisiwa.project3.bean.User;

/**
 * @author: nisiwa
 * @date: 2019-04-10 16:59
 */
public class CommentVo {
    User user;
    Comment comment;

    public CommentVo() {
    }

    public CommentVo(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
