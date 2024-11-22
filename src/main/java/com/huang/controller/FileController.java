package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.google.gson.Gson;
import com.huang.common.BaseResponse;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.model.dto.file.UploadFileRequest;
import com.huang.model.entity.User;
import com.huang.model.enums.FileUploadBizEnum;
import com.huang.constant.FileConstant;
import com.huang.exception.BusinessException;
import com.huang.manager.CosManager;
import com.huang.service.FileService;
import com.huang.service.UserService;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.qcloud.cos.utils.IOUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(MultipartFile file) {
        String imageUrl = fileService.uploadOssFile(file);
        return ResultUtils.success(imageUrl);
    }


}
