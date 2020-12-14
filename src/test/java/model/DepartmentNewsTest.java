package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentNewsTest {

    private DepartmentNews setUpNews(){
        Departments departments = new Departments("Accounting", "Assure financial records");
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

    @Test
    public void equalsChecksIfTheDepartmentNewsAreEqual() {
        DepartmentNews departmentNews = setUpNews();
        Departments departments = new Departments("Accounting", "Assure financial records");
        departments.setId(1);
        DepartmentNews secondDepartmentNews = new DepartmentNews("No work Tomorrow", departments.getId());
        assertTrue(departmentNews.equals(secondDepartmentNews));
    }
}