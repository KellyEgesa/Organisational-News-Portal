package Dao;

import model.Departments;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oDepartmentTest {

    private static Sql2oDepartment departmentDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp(){
        departmentDao = new Sql2oDepartment();
    }

    private Departments setUpDepartment(){
        return new Departments("Accounting", "Assure financial records");
    }

    private Departments setUpSecondDepartment(){
        return new Departments("Human Resource", "Wellfare of the employees");
    }

    @Test
    public void saveDepartmentSavesTheDepartmentInDB() {
        Departments departments = setUpDepartment();
        departmentDao.saveDepartment(departments);
        assertTrue(departmentDao.getAllDepartments().get(0).equals(departments));
    }

    @Test
    public void saveDepartmentSetsTheCorrectId() {
        Departments departments = setUpDepartment();
        int originalId = departments.getId();
        departmentDao.saveDepartment(departments);
        assertNotEquals(originalId, departments.getId());
    }

    @Test
    public void findByIdReturnsTheCorrect() {
        Departments departments = setUpDepartment();
        Departments secondDepartments = setUpSecondDepartment();
        departmentDao.saveDepartment(departments);
        int departmentId = departments.getId();
        departmentDao.saveDepartment(secondDepartments);
        assertEquals(departments.getId(), departmentDao.findDepartmentById(departmentId).getId());
    }

    @Test
    public void addDepartmentEmployeeNumbersAddsTheEmployeesNumber() {
        Departments departments = setUpDepartment();
        departmentDao.addDepartmentEmployeeNumbers(departments);
        assertEquals(1, departments.getDepartmentEmployees());
    }

    @Test
    public void deleteDepartmentByIdRemovesTheDepartment_1() {
        Departments departments = setUpDepartment();
        Departments secondDepartments = setUpSecondDepartment();
        departmentDao.saveDepartment(departments);
        int departmentId = departments.getId();
        departmentDao.saveDepartment(secondDepartments);
        departmentDao.deleteDepartmentById(departmentId);
        assertEquals(1, departmentDao.getAllDepartments().size());
    }

    @Test
    public void clearAllDepartmentsRemovesAllDepartments() {
        Departments departments = setUpDepartment();
        Departments secondDepartments = setUpSecondDepartment();
        departmentDao.saveDepartment(departments);
        departmentDao.saveDepartment(secondDepartments);
        departmentDao.clearAllDepartments();
        assertEquals(0, departmentDao.getAllDepartments().size());
    }
}