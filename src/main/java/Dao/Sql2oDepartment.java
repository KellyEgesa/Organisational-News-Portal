package Dao;

import model.Departments;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oDepartment implements DepartmentDao {
    @Override
    public void saveDepartment(Departments departments) {
        String sql = "INSERT INTO department (departmentName, departmentDescription, departmentEmployees) VALUES (:departmentName, :departmentDescription, :departmentEmployees)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("departmentName", departments.getDepartmentName())
                    .addParameter("departmentDescription", departments.getDepartmentDescription())
                    .addParameter("departmentEmployees", departments.getDepartmentEmployees())
                    .executeUpdate()
                    .getKey();
            departments.setId(id);
        }
    }

    @Override
    public List<Departments> getAllDepartments() {
        String sql = "SELECT * FROM department";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Departments.class);
        }
    }

    @Override
    public Departments findDepartmentById(int id) {
        String sql = "SELECT * FROM department WHERE id =:id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Departments.class);
        }
    }

    @Override
    public void addDepartmentEmployeeNumbers(Departments departments) {
        departments.setDepartmentEmployees();
        String sql = "UPDATE department SET departmentEmployees =:departmentEmployees WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", departments.getId())
                    .addParameter("departmentEmployees", departments.getDepartmentEmployees())
                    .executeUpdate();
        }
    }

    @Override
    public void deleteDepartmentById(int id) {
        String sql = "DELETE FROM department WHERE id =:id";
        String sql2 = "DELETE FROM news WHERE departmentId =:departmentId";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(sql2)
                    .addParameter("departmentId", id)
                    .executeUpdate();
        }

    }

    @Override
    public void clearAllDepartments() {
        String sql = "DELETE FROM department";
        String sql2 = "DELETE FROM news";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
            con.createQuery(sql)
                    .executeUpdate();
        }

    }
}
