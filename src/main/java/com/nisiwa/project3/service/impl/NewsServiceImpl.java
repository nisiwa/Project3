package com.nisiwa.project3.service.impl;

import com.nisiwa.project3.VO.VO;
import com.nisiwa.project3.bean.News;
import com.nisiwa.project3.mapper.NewsMapper;
import com.nisiwa.project3.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: nisiwa
 * @date: 2019-04-10 21:23
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<VO> queryAll() {
        return newsMapper.selectAll();
    }

    @Override
    public boolean insert(News news) {
        return newsMapper.insert(news) == 1;
    }

    @Override
    public News selectNewsById(int newsId) {
        return newsMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public boolean updateNewsCommentCountById(int newsId) {
        return newsMapper.updateNewsCommentCountById(newsId) == 1;
    }

    @Override
    public List<News> queryAllNews() {
        return newsMapper.selectAllNews();
    }

    @Override
    public boolean updateNewsLikeCountById(int newsId, int count) {
        return newsMapper.updateNewsLikeCountById(newsId, count) == 1;
    }
}
