package Dao;

import model.News;

import java.util.List;

public interface NewsDao {
    void deleteNewsById(int id);
    void clearAllNews();
    void updateDepartmentNewsById(int id, String newsInfo);
}
