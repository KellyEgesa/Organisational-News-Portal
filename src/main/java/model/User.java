package model;

public class User {
    private String userName;
    private String positionInCompany;
    private int departmentId;
    private int id;

    public User(String userName, String positionInCompany, int departmentId) {
        this.userName = userName;
        this.positionInCompany = positionInCompany;
        this.departmentId = departmentId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
