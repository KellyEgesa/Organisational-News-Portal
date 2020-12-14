package Dao;

import model.DepartmentNews;
import model.Departments;
import model.GeneralNews;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oDepartmentNewsTest {
    private static Sql2oDepartmentNews departmentNewsDao;
    private static Sql2oDepartment departmentDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        departmentDao = new Sql2oDepartment();
        departmentNewsDao = new Sql2oDepartmentNews();
    }

    private DepartmentNews setUpDepartmentNews(){
        Departments departments=  new Departments("Accounting", "Assure financial records");
        departmentDao.saveDepartment(departments);
        return new DepartmentNews("No work Tomorrow", departments.getId());
    }

    private DepartmentNews setUpSecondDepartmentNews(){
        Departments departments=  new Departments("Human Resource", "Wellfare of the employees");
        departmentDao.saveDepartment(departments);
        return new DepartmentNews("Meeting tomorrow Morning", departments.getId());
    }

    @Test
    public void saveGeneralNewsSetsIdCorrectly() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        int originalId = departmentNews.getId();
        departmentNewsDao.saveDepartmentNews(departmentNews);
        assertNotEquals(originalId, departmentNews.getId());
    }

    @Test
    public void saveDepartmentNewsSavesTheNewsInTheDb() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        departmentNewsDao.saveDepartmentNews(departmentNews);
        assertTrue(departmentNews.equals(departmentNewsDao.getAllDepartmentNews().get(0)));
    }

    @Test
    public void getAllDepartmentNewsReturnsAllDepartmentNews_2() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        DepartmentNews secondDepartmentNews  = setUpSecondDepartmentNews();
        departmentNewsDao.saveDepartmentNews(departmentNews);
        departmentNewsDao.saveDepartmentNews(secondDepartmentNews);
        assertEquals(2, departmentNewsDao.getAllDepartmentNews().size());
    }

    @Test
    public void clearAllDepartmentNewsRemovesAllDepartmentNews_0() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        DepartmentNews secondDepartmentNews  = setUpSecondDepartmentNews();
        departmentNewsDao.saveDepartmentNews(departmentNews);
        departmentNewsDao.saveDepartmentNews(secondDepartmentNews);
        departmentNewsDao.clearAllDepartmentNews();
        assertEquals(0, departmentNewsDao.getAllDepartmentNews().size());
    }

}