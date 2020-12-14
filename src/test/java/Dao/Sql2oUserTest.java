package Dao;

import model.Departments;
import model.User;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oUserTest {
    private static Sql2oDepartment departmentDao;
    private static Sql2oUser userDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        departmentDao = new Sql2oDepartment();
        userDao = new Sql2oUser();
    }

    private User setupUser() {
        Departments departments = new Departments("Accounting", "Assure financial records");
        departmentDao.saveDepartment(departments);
        return new User("Kelly Egesa", "Manager", "run day to day activities", departments.getId());
    }

    private User setupSecondUser() {
        Departments departments = new Departments("Human Resource", "Wellfare of the employees");
        departmentDao.saveDepartment(departments);
        return new User("Brian Kamau", "Clerk", "Data entry", departments.getId());
    }

    @Test
    public void saveUserSetsId() {
        User user = setupUser();
        int originalId = user.getId();
        userDao.saveUser(user);
        assertNotEquals(originalId, user.getId());
    }

    @Test
    public void saveUserSavesTheUserInTheDatabase_True() {
        User user = setupUser();
        userDao.saveUser(user);
        assertTrue(user.equals(userDao.getAllUsers().get(0)));
    }

    @Test
    public void getAllUsersReturnsAllUsers_2() {
        User user = setupUser();
        User secondUser = setupSecondUser();
        userDao.saveUser(user);
        userDao.saveUser(secondUser);
        assertEquals(2, userDao.getAllUsers().size());
    }

    @Test
    public void findUserByIdReturnsCorrectUser_True() {
        User user = setupUser();
        User secondUser = setupSecondUser();
        userDao.saveUser(user);
        int id = user.getId();
        userDao.saveUser(secondUser);
        assertTrue(user.equals(userDao.findUserById(id)));
    }

    @Test
    public void deleteUserByIdDeletesTheCorrectUser() {
        User user = setupUser();
        User secondUser = setupSecondUser();
        userDao.saveUser(user);
        int id = user.getId();
        userDao.saveUser(secondUser);
        userDao.deleteUserById(id);
        assertTrue(secondUser.equals(userDao.getAllUsers().get(0)));
    }

    @Test
    public void clearUsersRemovesAllUsers() {
        User user = setupUser();
        User secondUser = setupSecondUser();
        userDao.saveUser(user);
        int id = user.getId();
        userDao.saveUser(secondUser);
        userDao.clearUsers();
        assertEquals(0, userDao.getAllUsers().size());
    }
}