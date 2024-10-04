package com.hfdy.sky.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 地址簿
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("address_book")
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    //用户id
    @NotNull
    private Long userId;

    //收货人
    @NotNull
    private String consignee;

    //手机号
    @NotNull
    private String phone;

    //性别 0 女 1 男
    @NotNull
    private String sex;

    //省级区划编号
    @NotNull
    private String provinceCode;

    //省级名称
    @NotNull
    private String provinceName;

    //市级区划编号
    @NotNull
    private String cityCode;

    //市级名称
    @NotNull
    private String cityName;

    //区级区划编号
    @NotNull
    private String districtCode;

    //区级名称
    @NotNull
    private String districtName;

    //详细地址
    @NotNull
    private String detail;

    //标签
    @NotNull
    private String label;

    //是否默认 0否 1是
    @NotNull
    private Integer isDefault;
}
