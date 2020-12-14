package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User setupUser(){
        Departments departments = new Departments("Accounting", "Assure financial records");
        departments.setId(1);
        return new User("Kelly Egesa", "Manager", departments.getId());
    }

    @Test
    public void UserInstantiatesCorrectly() {
        User user = setupUser();
        assertTrue(user instanceof User);
    }

    @Test
    public void getUserNameReturnsTheCorrectUserName() {
        User user = setupUser();
        assertEquals("Kelly Egesa", user.getUserName());
    }

    @Test
    public void getPositionInCompanyReturnTheCorrectPosition_Manager() {
        User user = setupUser();
        assertEquals("Manager", user.getPositionInCompany());
    }

    @Test
    public void getDepartmentIdReturnsTheCorrectValue_1() {
        User user = setupUser();
        assertEquals(1, user.getDepartmentId());
    }

    @Test
    public void theIdIsCorrectlySet_1() {
        User user = setupUser();
        user.setId(1);
        assertEquals(1, user.getId());
    }
}