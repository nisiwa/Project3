package com.nisiwa.project3.mapper;

import com.nisiwa.project3.VO.CommentVo;
import com.nisiwa.project3.bean.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<CommentVo> queryAllCommentsByNewsId(int newsId);
}