package com.huang.service;

import com.huang.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface FileService {
    String uploadOssFile(MultipartFile file);
    String uploadQRCodeToOss(BufferedImage qrImage);
}
