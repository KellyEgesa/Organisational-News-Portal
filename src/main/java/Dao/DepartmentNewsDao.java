package Dao;

import model.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {
    List<DepartmentNews> getAllDepartmentNews();
    void saveDepartmentNews(DepartmentNews generalNews);
    DepartmentNews getDepartmentNewsById(int id);
    void clearAllDepartmentNews();
    List<DepartmentNews> getDepartmentNewsByDepartmentId(int id);
    void deleteDepartmentsByDepartmentId(int id);

}
