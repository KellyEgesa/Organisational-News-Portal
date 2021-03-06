package Dao;

import Dao.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    public void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/organisation_news_test", "kelly", "kelly@123");

    }

    @Override
    public void after(){
        try(Connection con = DB.sql2o.open()){
            String deleteUsers = "DELETE FROM users *;";
            String deleteNews = "DELETE FROM news *;";
            String deletedDepartment = "DELETE FROM department *;";

            con.createQuery(deleteUsers)
                    .executeUpdate();
            con.createQuery(deleteNews)
                    .executeUpdate();
            con.createQuery(deletedDepartment)
                    .executeUpdate();
        }
    }
}
