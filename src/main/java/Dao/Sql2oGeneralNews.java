package Dao;

import model.GeneralNews;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oGeneralNews implements GeneralNewsDao {
    private final String DATABASE_TYPE = "General News";

    @Override
    public List<GeneralNews> getAllGeneralNews() {
        String sql = "SELECT * FROM news WHERE type = :type";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", DATABASE_TYPE)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(GeneralNews.class);
        }
    }

    @Override
    public void saveGeneralNews(GeneralNews generalNews) {
        String sql = "INSERT INTO news (newsInfo, type) VALUES (:newsInfo, :type)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("newsInfo", generalNews.getNewsInfo())
                    .addParameter("type", DATABASE_TYPE)
                    .executeUpdate()
                    .getKey();
            generalNews.setId(id);
        }
    }

    @Override
    public GeneralNews getGeneralNewsById(int id) {
        String sql = "SELECT * FROM news WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(GeneralNews.class);
        }
    }

    @Override
    public void clearAllGeneralNews() {
        String sql = "DELETE FROM news WHERE type =:type";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("type", DATABASE_TYPE)
                    .executeUpdate();
        }
    }
}
