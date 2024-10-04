package com.hfdy.sky.server.controller.admin;


import com.hfdy.sky.common.constant.JwtClaimsConstant;
import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.properties.JwtProperties;
import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.common.utils.JwtUtil;
import com.hfdy.sky.pojo.dto.EmployeeDTO;
import com.hfdy.sky.pojo.dto.EmployeeLoginDTO;
import com.hfdy.sky.pojo.dto.EmployeePageQueryDTO;
import com.hfdy.sky.pojo.entity.Employee;
import com.hfdy.sky.pojo.vo.EmployeeLoginVO;
import com.hfdy.sky.server.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private JwtProperties jwtProperties;



    @PostMapping("/login")
    @ApiOperation("登录")
    public ApiResult<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return ApiResult.success(employeeLoginVO);
    }


    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public ApiResult<String> logout() {
        return ApiResult.success();
    }


    @PostMapping
    @ApiOperation("新增员工")
    public ApiResult<String> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        if (employeeService.getById(employeeDTO.getId()) != null) {
            return ApiResult.error("用户 " + employeeDTO.getName() + " " + MessageConstant.USER_ALREADY_EXISTSl);
        }
        employeeService.addEmployee(employeeDTO);
        return ApiResult.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询员工")
    public ApiResult<PageResult<Employee>> getEmployeePage(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询：{}", employeePageQueryDTO);
        PageResult<Employee> pageResult = employeeService.searchEmployeePage(employeePageQueryDTO);
        return ApiResult.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用/禁用员工账号")
    public ApiResult<Integer> startOrForbid(@PathVariable Integer status, @RequestParam("id") Long id) {
        return ApiResult.success(employeeService.startOrForbid(status, id));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取员工")
    public ApiResult<Employee> getById(@PathVariable Long id) {
        return ApiResult.success(employeeService.getById(id));
    }

    @PutMapping
    @ApiOperation("修改员工")
    public ApiResult<Integer> update(@RequestBody EmployeeDTO employeeDTO) {
        int rows = employeeService.update(employeeDTO);
        return ApiResult.success(rows);
    }
}
