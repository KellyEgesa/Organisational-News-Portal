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

    @Test
    public void saveDepartmentIdSavesTheDepartmentInDB() {
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

}