package com.hspedu.chapter26Proj.domain;

public class Employee {
    private Integer id;
    private String empId;
    private String password;
    private String name;
    private String job;

    public Employee() {
        // 无参构造器，apache-dbutils反射需要
    }

    public Employee(Integer id, String empId, String password, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.password = password;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", empId=" + empId + ", password=" + password
                + ", name=" + name + ", job=" + job + "]";
    }
}
