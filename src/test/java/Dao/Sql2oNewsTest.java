package Dao;

import model.DepartmentNews;
import model.Departments;
import model.GeneralNews;
import model.News;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oNewsTest {
    private static Sql2oGeneralNews generalNewsDao;
    private static Sql2oDepartmentNews departmentNewsDao;
    private static Sql2oDepartment departmentDao;
    private static Sql2oNews newsDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        departmentDao = new Sql2oDepartment();
        departmentNewsDao = new Sql2oDepartmentNews();
        generalNewsDao = new Sql2oGeneralNews();
        newsDao = new Sql2oNews();
    }

    private DepartmentNews setUpDepartmentNews(){
        Departments departments=  new Departments("Accounting", "Assure financial records");
        departmentDao.saveDepartment(departments);
        return new DepartmentNews("No work Tomorrow", departments.getId());
    }

    private GeneralNews setUpNews(){
        return new GeneralNews("No work Tomorrow");
    }

    @Test
    public void deleteNewsByIdDeletesTheNews(){
        GeneralNews generalNews = setUpNews();
        generalNewsDao.saveGeneralNews(generalNews);
        int generalNewsId = generalNews.getId();
        DepartmentNews departmentNews=  setUpDepartmentNews();
        departmentNewsDao.saveDepartmentNews(departmentNews);
        int departmentNewsId = departmentNews.getId();
        newsDao.deleteNewsById(generalNewsId);
        newsDao.deleteNewsById(departmentNewsId);
        assertEquals(0, generalNewsDao.getAllGeneralNews().size());
        assertEquals(0, departmentNewsDao.getAllDepartmentNews().size());
    }

    @Test
    public void updateDepartmentNewsByIdUpdatesTheNewsInfo() {
        GeneralNews generalNews = setUpNews();
        generalNewsDao.saveGeneralNews(generalNews);
        int generalNewsId = generalNews.getId();
        DepartmentNews departmentNews=  setUpDepartmentNews();
        departmentNewsDao.saveDepartmentNews(departmentNews);
        int departmentNewsId = departmentNews.getId();
        newsDao.updateDepartmentNewsById(generalNewsId, "Meeting Tomorrow");
        newsDao.updateDepartmentNewsById(departmentNewsId, "Meeting Tomorrow");
        assertEquals("Meeting Tomorrow", generalNewsDao.getGeneralNewsById(generalNewsId).getNewsInfo());
        assertEquals("Meeting Tomorrow", departmentNewsDao.getDepartmentNewsById(departmentNewsId).getNewsInfo());
    }

    @Test
    public void clearAllNewsDeletesAllNews() {
        GeneralNews generalNews = setUpNews();
        generalNewsDao.saveGeneralNews(generalNews);
        DepartmentNews departmentNews=  setUpDepartmentNews();
        departmentNewsDao.saveDepartmentNews(departmentNews);
        newsDao.clearAllNews();
        assertEquals(0, generalNewsDao.getAllGeneralNews().size());
        assertEquals(0, departmentNewsDao.getAllDepartmentNews().size());
    }
}