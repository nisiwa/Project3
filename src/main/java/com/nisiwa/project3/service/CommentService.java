package com.nisiwa.project3.service;

import com.nisiwa.project3.VO.CommentVo;
import com.nisiwa.project3.bean.Comment;

import java.util.List;

public interface CommentService {
    boolean insert(Comment comment);

    List<CommentVo> queryCommentVosByNewsId(int newsId);
}
