package Dao;

import model.News;

import java.util.List;

public interface NewsDao {
    List<News> getAllNews();
    void deleteNewsById(int id);
    void clearAllNews();
}
