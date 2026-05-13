package entity;

public class Department {
    private int departmentID;//department_id            int
    private String departmentName;//department_name     VARCHAR

    public Department() {

    }

//    public Department(String deptName) {
//        this.departmentID = 0;
//        this.departmentName = deptName;
//    }

    public Department(String departmentName) {
        this.departmentID = 0;
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
}
