package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentNewsTest {

    private DepartmentNews setUpNews(){
        Departments departments = new Departments("Accounting");
        departments.setId(1);
        return new DepartmentNews("No work Tomorrow", departments.getId());
    }

    @Test
    public void DepartmentNewsInstantiatesCorrectly() {
        DepartmentNews departmentNews = setUpNews();
        assertTrue(departmentNews instanceof DepartmentNews);
    }

    @Test
    public void DepartmentNewsRetrievesDepartmentId() {
        DepartmentNews departmentNews = setUpNews();
        assertEquals(1, departmentNews.getDepartmentId());
    }
}