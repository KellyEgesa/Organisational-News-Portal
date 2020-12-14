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
        String sql ="SELECT * FROM department";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Departments.class);
        }
    }

    @Override
    public Departments findDepartmentById(int id) {
        return null;
    }

    @Override
    public void addDepartmentEmployeeNumbers(int id) {

    }

    @Override
    public void deleteDepartmentById(int id) {

    }

    @Override
    public void clearAllDepartments() {

    }
}
