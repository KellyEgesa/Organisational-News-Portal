package Dao;

import model.GeneralNews;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oGeneralNewsTest {
    private static Sql2oGeneralNews generalNewsDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp(){
        generalNewsDao = new Sql2oGeneralNews();
    }

    private GeneralNews setUpNews(){
        return new GeneralNews("No work Tomorrow");
    }

    private GeneralNews setSecondUpNews(){
        return new GeneralNews("Meeting tomorrow @ 10");
    }


    @Test
    public void saveDepartmentSavesTheDepartmentInDB() {
        GeneralNews generalNews = setUpNews();
        generalNewsDao.saveGeneralNews(generalNews);
        assertTrue(generalNewsDao.getAllGeneralNews().get(0).equals(generalNews));
    }

    @Test
    public void saveGeneralNewsSetsIdCorrectly() {
        GeneralNews generalNews = setUpNews();
        int originalId = generalNews.getId();
        generalNewsDao.saveGeneralNews(generalNews);
        assertNotEquals(originalId, generalNews.getId());
    }

    @Test
    public void getAllGeneralNewsReturnsAllGeneralNews_2() {
        GeneralNews generalNews = setUpNews();
        GeneralNews secondGeneralNews = setSecondUpNews();
        generalNewsDao.saveGeneralNews(generalNews);
        generalNewsDao.saveGeneralNews(secondGeneralNews);
        assertEquals(2, generalNewsDao.getAllGeneralNews().size());
    }

    @Test
    public void getGeneralNewsByIdReturnsTheCorrectGeneralNews_True() {
        GeneralNews generalNews = setUpNews();
        GeneralNews secondGeneralNews = setSecondUpNews();
        generalNewsDao.saveGeneralNews(generalNews);
        int id = generalNews.getId();
        generalNewsDao.saveGeneralNews(secondGeneralNews);
        assertTrue(generalNews.equals(generalNewsDao.getGeneralNewsById(id)));
    }

    @Test
    public void clearAllGeneralNewsRemovesAllGeneralNews_0() {
        GeneralNews generalNews = setUpNews();
        GeneralNews secondGeneralNews = setSecondUpNews();
        generalNewsDao.saveGeneralNews(generalNews);
        generalNewsDao.saveGeneralNews(secondGeneralNews);
        generalNewsDao.clearAllGeneralNews();
        assertEquals(0, generalNewsDao.getAllGeneralNews().size());
    }
}