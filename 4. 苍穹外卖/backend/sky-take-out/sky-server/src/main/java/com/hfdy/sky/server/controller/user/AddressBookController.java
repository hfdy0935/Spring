package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.pojo.dto.AddressBookSetDefaultDTO;
import com.hfdy.sky.pojo.entity.AddressBook;
import com.hfdy.sky.server.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/2 10:01
 */
@RestController
@RequestMapping("/user/addressBook")
@Slf4j
@Api("用户地址相关接口")
public class AddressBookController {
    @Resource
    private AddressBookService addressBookService;

    @GetMapping("/list")
    @ApiOperation("获取用户地址列表")
    public ApiResult<List<AddressBook>> list() {
        log.info("获取用户地址");
        return ApiResult.success(addressBookService.list());
    }

    @PostMapping
    @ApiOperation("新增收货地址")
    public ApiResult<Integer> add(@RequestBody AddressBook addressBook) {
        log.info("新增收货地址");
        return ApiResult.success(addressBookService.add(addressBook));
    }

    @PutMapping("/default")
    @ApiOperation("设为默认收货地址")
    public ApiResult<Integer> setDefaultAddress(@RequestBody AddressBookSetDefaultDTO addressBookSetDefaultDTO) {
        log.info("设置默认收货地址：{}", addressBookSetDefaultDTO);
        return ApiResult.success(addressBookService.setDefaultAddress(addressBookSetDefaultDTO));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取收货地址详情")
    public ApiResult<AddressBook> getDetailById(@PathVariable("id") Long id, @RequestParam("id") Long id1) {
        // 前端传了两个一模一样的id...
        // 获取收货地址列表
        log.info("根据id获取收货地址详情，{}", id);
        return ApiResult.success(addressBookService.getById(id));
    }

    @PutMapping
    @ApiOperation("修改收货地址")
    public ApiResult<Void> updateAddress(@RequestBody AddressBook addressBook) {
        log.info("修改收货地址：{}", addressBook);
        addressBookService.changeAddress(addressBook);
        return ApiResult.success();
    }

    // 前端请求多了个/
    @DeleteMapping("/")
    @ApiOperation("删除收货地址")
    public ApiResult<Void> deleteAddress(@RequestParam("id") Long id) {
        log.info("删除收货地址：{}", id);
        addressBookService.deleteAddress(id);
        return ApiResult.success();
    }

    @GetMapping("/default")
    @ApiOperation("获取用户默认收货地址")
    public ApiResult<AddressBook> getDefaultAddress(){
        log.info("获取用户默认收货地址");
        return ApiResult.success(addressBookService.getDefaultAddress());
    }

}
