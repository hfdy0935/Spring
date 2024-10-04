package com.hfdy.sky.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.utils.ExceptionUtil;
import com.hfdy.sky.pojo.dto.AddressBookSetDefaultDTO;
import com.hfdy.sky.pojo.entity.AddressBook;
import com.hfdy.sky.server.mapper.AddressBookMapper;
import com.hfdy.sky.server.service.AddressBookService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/2 09:58
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
    @Resource
    private AddressBookMapper addressBookMapper;

    @Override
    public List<AddressBook> list() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getUserId, userId);
        return addressBookMapper.selectList(addressBookLambdaQueryWrapper);
    }

    @Override
    public int add(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0); // 默认不是默认收货地址，需要后面确认再改
        return addressBookMapper.insert(addressBook);
    }

    @Override
    public int setDefaultAddress(AddressBookSetDefaultDTO addressBookSetDefaultDTO) {
        AddressBook addressBook = addressBookMapper.selectById(addressBookSetDefaultDTO.getId());
        ExceptionUtil.throwIf(addressBook == null, "不存在该收货地址");
        // 把原来的改成0
        LambdaUpdateWrapper<AddressBook> addressBookLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        addressBookLambdaUpdateWrapper.eq(AddressBook::getIsDefault, 1).set(AddressBook::getIsDefault, 0);
        addressBookMapper.update(addressBookLambdaUpdateWrapper);

        addressBook.setIsDefault(1);
        boolean res = addressBookMapper.insertOrUpdate(addressBook);
        if (res) return 1;
        return 0;
    }

    @Override
    public AddressBook getById(Long id) {
        return addressBookMapper.selectById(id);
    }

    @Override
    public void changeAddress(AddressBook addressBook) {
        AddressBook addressBook1 = addressBookMapper.selectById(addressBook.getId());
        ExceptionUtil.throwIf(addressBook1 == null, "收货地址不存在");
        addressBookMapper.insertOrUpdate(addressBook);
    }

    @Override
    public void deleteAddress(Long id) {
        AddressBook addressBook = addressBookMapper.selectById(id);
        Long userId = BaseContext.getCurrentId();
        ExceptionUtil.throwIf(!userId.equals(addressBook.getUserId()), "要删除的地址不是当前用户的地址");
        addressBookMapper.deleteById(id);
        // 如果删的是默认地址并且还存在收货地址，则把第一个改成默认地址
        if (addressBook.getIsDefault().equals(1)) {
            LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
            addressBookLambdaQueryWrapper.eq(AddressBook::getUserId, userId);
            AddressBook addressBook1 = addressBookMapper.selectOne(addressBookLambdaQueryWrapper);
            if (addressBook1 != null) {
                addressBook1.setIsDefault(1);
                addressBookMapper.update(addressBook1, null);
            }
        }
    }

    @Override
    public AddressBook getDefaultAddress() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getUserId, userId)
                .eq(AddressBook::getIsDefault, 1);
        return addressBookMapper.selectOne(addressBookLambdaQueryWrapper);
    }
}
