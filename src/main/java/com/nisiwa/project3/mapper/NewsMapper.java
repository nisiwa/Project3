package com.nisiwa.project3.mapper;

import com.nisiwa.project3.VO.VO;
import com.nisiwa.project3.bean.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    List<VO> selectAll();

    int updateByPrimaryKey(News record);

    int updateNewsCommentCountById(int newsId);

    List<News> selectAllNews();

    int updateNewsLikeCountById(@Param("newsId") int newsId, @Param("count") int count);
}