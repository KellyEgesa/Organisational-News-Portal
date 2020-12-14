package Dao;

import model.News;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oNews implements NewsDao {

    @Override
    public void updateDepartmentNewsById(int id, String newsInfo) {
        String sql = "UPDATE news SET newsInfo =:newsInfo WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("newsInfo", newsInfo)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteNewsById(int id) {
        String sql = "DELETE FROM news WHERE id =:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAllNews() {
        String sql = "DELETE FROM news";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}
