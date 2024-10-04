package com.hspedu.chapter26Proj.service;

import com.hspedu.chapter26Proj.dao.EmployeeDAO;
import com.hspedu.chapter26Proj.domain.Employee;

/**
 * 调用EmployeeDAO对象
 * 完成对employee表的各种操作
 */
public class EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * 获取用户
     *
     * @param empId
     * @param password
     * @return
     */
    public Employee getEmployeeByIdAndPassword(String empId, String password) {
        String sql = "select * from employee where empId=? and password=md5(?)";
        return employeeDAO.querySingle(sql, Employee.class, empId, password);
    }
}
