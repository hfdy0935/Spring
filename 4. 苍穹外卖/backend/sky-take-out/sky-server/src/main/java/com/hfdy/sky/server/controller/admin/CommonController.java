package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author hf-dy
 * @date 2024/9/29 17:01
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api("通用接口")
public class CommonController {
    @Resource
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public ApiResult<String> upload(@RequestPart MultipartFile file) {
        log.info(file.getName());
        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        // 后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String filename = UUID.randomUUID() + extension;
        try {
            String filepath = aliOssUtil.upload(file.getBytes(), filename);
            return ApiResult.success(filepath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
            return ApiResult.error(MessageConstant.UPLOAD_FAILED);
        }
    }
}
