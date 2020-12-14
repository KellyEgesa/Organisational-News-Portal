package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    private News setUpNews(){
        return new News("No work Tomorrow");
    }

    @Test
    public void newsInstantiatesCorrectly() {
        News news = setUpNews();
        assertTrue(news instanceof News);
    }

    @Test
    public void getNewsInfoReturnsCorrectlyNewsInfo() {
        News news = setUpNews();
        assertEquals("No work Tomorrow", news.getNewsInfo());
    }

    @Test
    public void setIdSetsIdCorrectlyAndReturnsCorrectId_1() {
        News news = setUpNews();
        news.setId(1);
        assertEquals(1, news.getId());
    }

    @Test
    public void equalsChecksIfTheNewsAreEqualCorrectly() {
        News news = setUpNews();
        News secondNews = new News("No work Tomorrow");
        assertTrue(news.equals(secondNews));
    }
}