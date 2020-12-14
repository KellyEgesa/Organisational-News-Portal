package model;

import java.util.Objects;

public class Departments {
    private String departmentName;
    private String departmentDescription;
    private int departmentEmployees;
    private int id;

    public Departments(String departmentName, String departmentDescription) {
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.departmentEmployees = 0;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public int getDepartmentEmployees() {
        return departmentEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartmentEmployees() {
        this.departmentEmployees++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments that = (Departments) o;
        return getId() == that.getId() &&
                Objects.equals(getDepartmentName(), that.getDepartmentName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentName(), getId());
    }
}
