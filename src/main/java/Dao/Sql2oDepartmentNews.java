package Dao;

import model.DepartmentNews;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oDepartmentNews implements DepartmentNewsDao {
    private final String DATABASE_TYPE = "Department News";
    @Override
    public List<DepartmentNews> getAllDepartmentNews() {
        String sql = "SELECT * FROM news WHERE type = :type";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", DATABASE_TYPE)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(DepartmentNews.class);
        }
    }

    @Override
    public void saveDepartmentNews(DepartmentNews departmentNews) {
        String sql = "INSERT INTO news (newsInfo, type, departmentId) VALUES (:newsInfo, :type, :departmentId)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("newsInfo", departmentNews.getNewsInfo())
                    .addParameter("departmentId", departmentNews.getDepartmentId())
                    .addParameter("type", DATABASE_TYPE)
                    .executeUpdate()
                    .getKey();
            departmentNews.setId(id);
        }
    }

    @Override
    public DepartmentNews getDepartmentNewsById(int id) {
        String sql = "SELECT * FROM news WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }

    @Override
    public void clearAllDepartmentNews() {
        String sql = "DELETE FROM news WHERE type =:type";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("type", DATABASE_TYPE)
                    .executeUpdate();
        }
    }
}
