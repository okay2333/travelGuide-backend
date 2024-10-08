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
    private UserService userService;

    @Resource
    private CosManager cosManager;

    /**
     * 文件上传
     * @param file
     * @param prefix
     * @return
     */
    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, String prefix) {
        String accessKey = "Ce0HbXmIEXsmEpw_Z8LhZGqNW6QHKJaJKS36Qqe4";
        //构造一个带指定 Region 对象的配置类，指定存储区域，和存储空间选择的区域一致
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String secretKey = "-d_RPCSw8eUckuJgPaHSHJE2E8olsnB1mUpCjyxr";
        String bucket =  "web-dev-oss";
        String url =  "http://sl0pp5u7u.sabkt.gdipper.com/";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID().toString()+"."+prefix;
        try {

            //得到本地文件的字节数组
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            //认证
            Auth auth = Auth.create(accessKey, secretKey);
            //认证通过后得到token（令牌）
            String upToken = auth.uploadToken(bucket);
            try {
                //上传文件,参数：字节数组，key，token令牌
                //key: 建议我们自已生成一个不重复的名称
                Response response = uploadManager.put(bytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                String imageUrl = url + putRet.key;
                return imageUrl;
            } catch (QiniuException ex) {
                log.error("上传文件至OSS[异常]",ex);

            }
        } catch (IOException ex) {
            log.error("上传文件至OSS[异常]",ex);

        }
        return null;
    }


}
