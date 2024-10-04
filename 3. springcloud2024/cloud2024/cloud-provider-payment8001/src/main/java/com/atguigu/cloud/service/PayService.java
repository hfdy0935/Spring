package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/8/31 10:24
 */
public interface PayService {
    int add(PayDTO payDTO);

    int delete(Integer id);

    int update(Pay pay);

    PayDTO getById(Integer id);

    List<Pay> getAll();
}
