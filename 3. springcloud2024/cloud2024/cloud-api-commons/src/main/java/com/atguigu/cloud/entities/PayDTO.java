package com.atguigu.cloud.entities;

/**
 * @author hf-dy
 * @date 2024/8/31 17:00
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayDTO implements Serializable {
    private Integer id;
    private String payNo;
    private String orderNo;
    private Integer userId;
    private BigDecimal amount;
}