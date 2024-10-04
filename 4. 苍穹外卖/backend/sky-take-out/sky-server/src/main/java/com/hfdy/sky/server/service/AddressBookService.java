package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.pojo.dto.AddressBookSetDefaultDTO;
import com.hfdy.sky.pojo.entity.AddressBook;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/2 09:58
 */

public interface AddressBookService extends IService<AddressBook> {

    /**
     * 获取用户所有地址
     *
     * @return
     */
    List<AddressBook> list();

    /**
     * 新增收货地址
     *
     * @param addressBook
     * @return
     */
    int add(AddressBook addressBook);

    /**
     * 设置默认收货地址
     *
     * @param addressBookSetDefaultDTO
     * @return
     */
    int setDefaultAddress(AddressBookSetDefaultDTO addressBookSetDefaultDTO);

    /**
     * 根据id获取收货地址
     *
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * 修改收货地址
     *
     * @param addressBook
     * @return
     */
    void changeAddress(AddressBook addressBook);

    /**
     * 根据id删除收货地址
     *
     * @param id
     */
    void deleteAddress(Long id);

    /**
     * 获取用户默认收货地址
     *
     * @return
     */
    AddressBook getDefaultAddress();
}
