package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hf-dy
 * @date 2024/8/31 10:21
 */

@Service
public class PayServiceImpl implements PayService {
    @Resource
    private PayMapper payMapper;

    @Override
    public int add(PayDTO payDTO) {
        Pay pay = new Pay();
        pay.setId(payDTO.getId());
        pay.setPayNo(payDTO.getPayNo());
        pay.setOrderNo(payDTO.getOrderNo());
        pay.setUserId(payDTO.getUserId());
        pay.setAmount(payDTO.getAmount());
        Byte deleted = 0;
        pay.setDeleted(deleted);
        pay.setCreateTime(new Date());
        pay.setUpdateTime(new Date());
        System.out.println(pay);
        return payMapper.insert(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public PayDTO getById(Integer id) {
        if (id == 4) {
            throw new RuntimeException("id不能为4");
        }
        Pay pay = payMapper.selectByPrimaryKey(id);
        PayDTO payDTO = new PayDTO();
        if (pay == null) {
            return payDTO;
        }
        payDTO.setId(pay.getId());
        payDTO.setPayNo(pay.getPayNo());
        payDTO.setOrderNo(pay.getOrderNo());
        payDTO.setUserId(pay.getUserId());
        payDTO.setAmount(pay.getAmount());
        return payDTO;
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
