package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneralNewsTest {

    private GeneralNews setUpNews(){
        return new GeneralNews("No work Tomorrow");
    }

    @Test
    public void GeneralNewsInstantiatesCorrectly() {
        GeneralNews generalNews = setUpNews();
        assertTrue(generalNews instanceof GeneralNews);
    }
}