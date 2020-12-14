package model;

import java.util.Objects;

public class Departments {
    public String departmentName;
    public int id;

    public Departments(String departmentName){
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
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
