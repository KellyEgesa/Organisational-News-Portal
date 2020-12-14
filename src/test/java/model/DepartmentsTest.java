package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentsTest {

    private Departments setUpDepartment(){
        return new Departments("Accounting", "Assure financial records");
    }

    @Test
    public void departmentsInstantiatesCorrectly() {
        Departments departments = setUpDepartment();
        assertTrue(departments instanceof Departments);
    }

    @Test
    public void departmentsSetsNameCorrectly_Accounting() {
        Departments departments = setUpDepartment();
        assertEquals("Accounting", departments.getDepartmentName());
    }

    @Test
    public void getDepartmentDescriptionReturnsTheCorrectDescription() {
        Departments departments = setUpDepartment();
        assertEquals("Assure financial records", departments.getDepartmentDescription());
    }

    @Test
    public void numberOfEmloyeesIsAddedCorrectly() {
        Departments departments = setUpDepartment();
        departments.setDepartmentEmployees();
        assertEquals(1, departments.getDepartmentEmployees());
    }

    @Test
    public void setIdSetsDepartmentIdCorrectly_1() {
        Departments departments = setUpDepartment();
        departments.setId(1);
        assertEquals(1, departments.getId());
    }

    @Test
    public void equalsChecksIfDepartmentsAreIdentical_True() {
        Departments departments = setUpDepartment();
        Departments secondDepartments = new Departments("Accounting", "Assure financial records");
        assertTrue(departments.equals(secondDepartments));
    }
}