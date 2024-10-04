package com.hfdy.sky.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("employee")
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    @TableField("username")
    private String username;
    @TableField("name")
    private String name;
    @TableField("password")
    private String password;
    @TableField("phone")
    private String phone;
    @TableField("sex")
    private String sex;
    @TableField("id_number")
    private String idNumber;
    @TableField("status")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("create_user")
    private Long createUser;
    @TableField("update_user")
    private Long updateUser;
}
