package Dao;

import model.News;
import org.junit.Rule;

import static org.junit.Assert.*;

public class Sql2oNewsTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private News setUpNews(){
        return new News("No work Tomorrow");
    }
}