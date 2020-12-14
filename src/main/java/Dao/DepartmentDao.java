package Dao;

import model.Departments;

import java.util.List;

public interface DepartmentDao {
    void saveDepartment(Departments departments);
    List<Departments> getAllDepartments();
    Departments findDepartmentById(int id);
    void addDepartmentEmployeeNumbers(int id);
    void deleteDepartmentById(int id);
    void clearAllDepartments();


}
