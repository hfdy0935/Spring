package com.hfdy.sky.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hfdy.sky.pojo.dto.GoodsSalesDTO;
import com.hfdy.sky.pojo.entity.Orders;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/2 11:47
 */

public interface OrderMapper extends BaseMapper<Orders> {

    /**
     * 查询商品销量排名
     *
     * @param begin
     * @param end
     * @return
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);
}
