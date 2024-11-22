package com.huang.utils;



import java.awt.image.BufferedImage;
import java.io.*;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.huang.service.FileService;
import com.huang.service.impl.FileServiceImpl;


import javax.annotation.Resource;

public class QRCodeUtil {
    @Resource
    private FileService fileService;
    // 生成二维码并保存为图片
    public static BufferedImage  generateQRCode(String content) throws IOException {
        // 创建二维码配置
        QrConfig config = new QrConfig();
        config.setWidth(300);  // 设置二维码宽度
        config.setHeight(300); // 设置二维码高度
        config.setMargin(1);   // 设置二维码边距

        // 生成二维码图像
        BufferedImage image = QrCodeUtil.generate(content, config);
        return image;  // 返回上传后的文件 URL
    }
    public static void main(String[] args) throws IOException {
        String orderNumber = "123456789";  // 订单号
        String qrCodePath = "path/to/qr-code.png";
        BufferedImage bufferedImage = generateQRCode("订单号:" + orderNumber);
        System.out.println("二维码生成成功！");
         FileService fileService = new FileServiceImpl();
        String s = fileService.uploadQRCodeToOss(bufferedImage);
        System.out.println(s);
    }
}
