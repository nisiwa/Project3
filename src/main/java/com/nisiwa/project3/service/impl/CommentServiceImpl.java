package com.nisiwa.project3.service.impl;

import com.nisiwa.project3.VO.CommentVo;
import com.nisiwa.project3.bean.Comment;
import com.nisiwa.project3.mapper.CommentMapper;
import com.nisiwa.project3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-11 18:34
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment) == 1;
    }

    @Override
    public List<CommentVo> queryCommentVosByNewsId(int newsId) {
        return commentMapper.queryAllCommentsByNewsId(newsId);
    }
}
