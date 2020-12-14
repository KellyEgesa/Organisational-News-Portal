package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    private News setUpNews(){
        return new News("No work Tomorrow");
    }

    @Test
    public void newsInstantiatesCorrestly() {
        News news = setUpNews();
        assertTrue(news instanceof News);
    }

    @Test
    public void getNewsInfo() {
    }

    @Test
    public void getId() {
    }

    @Test
    public void setId() {
    }

    @Test
    public void testEquals() {
    }
}