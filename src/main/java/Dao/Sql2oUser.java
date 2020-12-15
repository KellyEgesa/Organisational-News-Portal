package Dao;

import model.Departments;
import model.User;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oUser implements UserDao {

    private static Sql2oDepartment departmentDao = new Sql2oDepartment();;

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (userName, positionInCompany, userRole, departmentId) VALUES (:userName, :positionInCompany,:userRole ,:departmentId)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("userName", user.getUserName())
                    .addParameter("positionInCompany", user.getPositionInCompany())
                    .addParameter("userRole", user.getUserRole())
                    .addParameter("departmentId", user.getDepartmentId())
                    .executeUpdate()
                    .getKey();
            user.setId(id);
            Departments departments = departmentDao.findDepartmentById(user.getDepartmentId());
            departmentDao.addDepartmentEmployeeNumbers(departments);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public List<User> getUsersByDepartmentId(int id) {
        String sql = "SELECT * FROM users WHERE departmentId = :departmentId";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("departmentId", id)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id =:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }

    }

    @Override
    public void clearUsers() {
        String sql = "DELETE FROM users";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }

    }
}
