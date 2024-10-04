package com.hfdy.sky.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.*;
import com.hfdy.sky.pojo.entity.Employee;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer;

public interface EmployeeService extends IService<Employee> {
    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    void addEmployee(EmployeeDTO employeeDTO);


    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    PageResult<Employee> searchEmployeePage(EmployeePageQueryDTO employeePageQueryDTO);


    /**
     * 启用/禁用员工账号
     *
     * @param status
     * @param id
     */
    int startOrForbid(Integer status, Long id);

    /**
     * 修改员工
     *
     * @param employeeDTO
     */
    int update(EmployeeDTO employeeDTO);
}
