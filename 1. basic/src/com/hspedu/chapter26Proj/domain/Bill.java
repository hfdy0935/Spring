package com.hspedu.chapter26Proj.domain;

import java.util.Date;

public class Bill {
    private Integer id;
    private String billId;
    private Integer empId; // 哪个用户创建的
    private Integer diningTableId; // 餐桌id
    private Integer menuId; // 菜id
    private Integer num; // 菜数量
    private Date billDateTime; // 创建时间
    private Double money; // 总金额
    private Integer status; // 账单状态，0未支付，1已支付

    public Bill() {
    }

    public Bill(Integer id, String billId, Integer empId, Integer diningTableId, Integer menuId, Integer num, Date billDateTime, Double money, Integer status) {
        this.id = id;
        this.billId = billId;
        this.empId = empId;
        this.diningTableId = diningTableId;
        this.menuId = menuId;
        this.num = num;
        this.billDateTime = billDateTime;
        this.money = money;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getBillDateTime() {
        return billDateTime;
    }

    public void setBillDateTime(Date billDateTime) {
        this.billDateTime = billDateTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\t\t\t " + billId + "\t\t" + empId + "\t\t" + diningTableId + "\t\t" + menuId + "\t\t" + num + "\t\t" + billDateTime + "\t\t" + money + "\t\t" + (status == 1 ? "已支付" : "未支付");
    }
}
