package model;

import Dao.Sql2oDepartment;

import java.util.Objects;

public class User {
    private String userName;
    private String positionInCompany;
    private int departmentId;
    private String userRole;
    private int id;

    private static Sql2oDepartment departmentDao = new Sql2oDepartment();

    public User(String userName, String positionInCompany,String userRole, int departmentId) {
        this.userName = userName;
        this.positionInCompany = positionInCompany;
        this.departmentId = departmentId;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public String getPositionInCompany() {
        return positionInCompany;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Departments getDepartment(){
        return departmentDao.findDepartmentById(departmentId);
    }

    public String getUserRole() {
        return userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getDepartmentId() == user.getDepartmentId() &&
                getId() == user.getId() &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getPositionInCompany(), user.getPositionInCompany()) &&
                Objects.equals(getUserRole(), user.getUserRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPositionInCompany(), getDepartmentId(), getUserRole(), getId());
    }
}
