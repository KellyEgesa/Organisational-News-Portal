package model;

public class DepartmentNews extends News{
    private int departmentId;

    public DepartmentNews(String news, int departmentId){
        super(news);
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }
}
