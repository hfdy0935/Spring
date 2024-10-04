package com.hfdy.sky.pojo.vo;


import com.hfdy.sky.pojo.entity.DishFlavor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜品响应模型")
public class DishVO implements Serializable {
    @ApiModelProperty("菜品id")
    private Long id;
    @ApiModelProperty("菜品名称")
    private String name;
    @ApiModelProperty("菜品分类id")
    private Long categoryId;
    @ApiModelProperty("菜品价格")
    private BigDecimal price;
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("描述信息")
    private String description;
    @ApiModelProperty("0 停售 1 起售")
    private Integer status;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("菜品关联的口味")
    private List<DishFlavor> flavors = new ArrayList<>();

    //private Integer copies;
}
