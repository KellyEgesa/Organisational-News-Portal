package Dao;

import model.GeneralNews;

import java.util.List;

public interface GeneralNewsDao {
    List<GeneralNews> getAllGeneralNews();
    void saveGeneralNews(GeneralNews generalNews);
    GeneralNews getGeneralNewsById(int id);
    void updateGeneralNewsById(int id);
}
