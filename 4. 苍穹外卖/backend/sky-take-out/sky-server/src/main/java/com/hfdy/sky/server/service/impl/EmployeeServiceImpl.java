package com.hfdy.sky.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.constant.PasswordConstant;
import com.hfdy.sky.common.constant.StatusConstant;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.exception.AccountLockedException;
import com.hfdy.sky.common.exception.AccountNotFoundException;
import com.hfdy.sky.common.exception.HasNoLoginPermissionException;
import com.hfdy.sky.common.exception.PasswordErrorException;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.EmployeeDTO;
import com.hfdy.sky.pojo.dto.EmployeeLoginDTO;
import com.hfdy.sky.pojo.dto.EmployeePageQueryDTO;
import com.hfdy.sky.pojo.entity.Employee;
import com.hfdy.sky.server.mapper.EmployeeMapper;
import com.hfdy.sky.server.service.EmployeeService;
import jakarta.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        if (!username.equals("admin")) {
            throw new HasNoLoginPermissionException("无登陆权限");
        }
        String password = employeeLoginDTO.getPassword();
        //1、根据用户名查询数据库中的数据
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, username);
        Employee employee = employeeMapper.selectOne(lambdaQueryWrapper);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 账号被禁用
        if (employee.getStatus() == 0) {
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    @Override

    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        // 帐号状态
        employee.setStatus(StatusConstant.ENABLE);
        // 默认密码
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // 创建人、修改人
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());
        // 插入
        employeeMapper.insert(employee);
    }

    @Override
    public PageResult<Employee> searchEmployeePage(EmployeePageQueryDTO employeePageQueryDTO) {
        long current = employeePageQueryDTO.getPage();
        long size = employeePageQueryDTO.getPageSize();
        Page<Employee> page = new Page<>(current, size);
        // 过滤员工姓名（可以重复）
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(employeePageQueryDTO.getName() != null, Employee::getName, employeePageQueryDTO.getName());
        employeeMapper.selectPage(page, lambdaQueryWrapper);
        // 分页
        PageResult<Employee> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getRecords());
        return pageResult;
    }

    @Override
    public int startOrForbid(Integer status, Long id) {
        if (status != 0 && status != 1) {
            return 0;
        }
        LambdaUpdateWrapper<Employee> lambdaUpdateChainWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateChainWrapper.eq(Employee::getId, id).set(Employee::getStatus, status);
        return employeeMapper.update(lambdaUpdateChainWrapper);
    }

    @Override
    public int update(EmployeeDTO employeeDTO) {
        LambdaUpdateWrapper<Employee> lambdaUpdateChainWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateChainWrapper.eq(Employee::getId, employeeDTO.getId())
                .set(Employee::getUsername, employeeDTO.getUsername())
                .set(Employee::getName, employeeDTO.getName())
                .set(Employee::getPhone, employeeDTO.getPhone())
                .set(Employee::getSex, employeeDTO.getSex())
                .set(Employee::getIdNumber, employeeDTO.getIdNumber())
                .set(Employee::getUpdateTime, LocalDateTime.now())
                .set(Employee::getUpdateUser, BaseContext.getCurrentId());
        return employeeMapper.update(lambdaUpdateChainWrapper);
    }
}

