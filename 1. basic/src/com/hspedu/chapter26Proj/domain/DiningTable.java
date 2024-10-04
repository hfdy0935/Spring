package com.hspedu.chapter26Proj.domain;


public class DiningTable {
    private Integer id;
    private String empId;
    private Integer status;
    private String orderName;
    private String orderTel;

    public DiningTable() {
    }

    public DiningTable(Integer id, String empId, Integer status, String orderName, String orderTel) {
        this.id = id;
        this.empId = empId;
        this.status = status;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t" + (status.equals(1) ? "可预订" : "已预订") + "\t  " + orderName + "\t\t\t " + orderTel;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

}
