package com.hfdy.sky.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    //类型: 1菜品分类 2套餐分类
    @TableField("type")
    private Integer type;
    //分类名称
    @TableField("name")
    private String name;
    //顺序
    @TableField("sort")
    private Integer sort;
    //分类状态 0标识禁用 1表示启用
    @TableField("status")
    private Integer status;
    //创建时间
    @TableField("create_time")
    private LocalDateTime createTime;
    //更新时间
    @TableField("update_time")
    private LocalDateTime updateTime;
    //创建人
    @TableField("create_user")
    private Long createUser;
    //修改人
    @TableField("update_user")
    private Long updateUser;
}
