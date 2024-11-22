    package com.huang.service.impl;

    import cn.hutool.core.lang.UUID;
    import com.google.gson.Gson;
    import com.huang.service.FileService;
    import com.qcloud.cos.utils.IOUtils;
    import com.qiniu.common.QiniuException;
    import com.qiniu.http.Response;
    import com.qiniu.storage.Configuration;
    import com.qiniu.storage.Region;
    import com.qiniu.storage.UploadManager;
    import com.qiniu.storage.model.DefaultPutRet;
    import com.qiniu.util.Auth;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;

    import javax.imageio.ImageIO;
    import java.awt.image.BufferedImage;
    import java.io.ByteArrayOutputStream;
    import java.io.IOException;

    @Service
    @Slf4j
    public class FileServiceImpl implements FileService {
        @Value("${cos.client.accessKey}")
        private String ossAccessKey;

        @Value("${cos.client.secretKey}")
        private String ossSecretKey;

        @Value("${cos.client.bucket}")
        private String ossBucket;

        @Value("${cos.client.url}")
        private String ossUrl;
        @Override
        public String uploadOssFile(MultipartFile file) {
            String accessKey = ossAccessKey;
            //构造一个带指定 Region 对象的配置类，指定存储区域，和存储空间选择的区域一致
            Configuration cfg = new Configuration(Region.autoRegion());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String secretKey = ossSecretKey;
            String bucket = ossBucket;
            String url = ossUrl;
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = UUID.randomUUID().toString();
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
        // 新增上传二维码到OSS的方法
        @Override
        public String uploadQRCodeToOss(BufferedImage qrImage) {
            String accessKey = ossAccessKey;
            Configuration cfg = new Configuration(Region.autoRegion());
            UploadManager uploadManager = new UploadManager(cfg);

            String secretKey = ossSecretKey;
            String bucket = ossBucket;
            String url = ossUrl;

            // 生成唯一的文件名
            String key = UUID.randomUUID().toString() + ".png"; // .png可以根据需要调整格式

            try {
                // 将 BufferedImage 转换为字节数组
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(qrImage, "PNG", baos); // 转换为 PNG 格式
                byte[] fileBytes = baos.toByteArray();

                // 创建上传凭证
                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);

                // 上传文件
                Response response = uploadManager.put(fileBytes, key, upToken);

                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                String imageUrl = url + putRet.key;
                return imageUrl; // 返回上传后的文件 URL

            } catch (IOException ex) {
                log.error("上传二维码至OSS时发生错误", ex);
            } catch (Exception ex) {
                log.error("上传二维码至OSS时发生未知错误", ex);
            }
            return null; // 上传失败返回 null
        }
    }
