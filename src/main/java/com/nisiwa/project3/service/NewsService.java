package com.nisiwa.project3.service;

import com.nisiwa.project3.VO.VO;
import com.nisiwa.project3.bean.News;

import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-10 21:17
 */
public interface NewsService {
    List<VO> queryAll();

    boolean insert(News news);

    News selectNewsById(int newsId);

    boolean updateNewsCommentCountById(int newsId);

    List<News> queryAllNews();

    boolean updateNewsLikeCountById(int parseInt, int i);
}
