package com.liming.controller.apublic;

import com.liming.commons.resultformat.Result;
import com.liming.config.LimingConfig;
import com.liming.totalenum.pictureupload.PictureSuffixEnum;
import com.liming.utils.CosClientUtil;
import com.qcloud.cos.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController {

    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private LimingConfig limingConfig;

    @PostMapping("/uploadDocument")
    public String uploadDocument(MultipartFile file){
        File toFile = null;
        StringBuffer path = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        try {
            //获取文件全名
            String fileName = file.getOriginalFilename();
            if (fileName.isEmpty()) {
                return Result.error("文件不能为空！").toJsonString();
            }
            //对文文件的全名进行截取然后在后缀名进行删选。
            int begin = fileName.indexOf(".");
            int last = fileName.length();
            //获得文件后缀名
            String fileSuffix = file.getOriginalFilename().substring(begin, last);
            //判断是否支持此文件格式
            PictureSuffixEnum pictureSuffixEnum = PictureSuffixEnum.getPictureSuffix(fileSuffix);
            if (pictureSuffixEnum == null)
                return Result.error("暂不支持"+fileName+"类型的文件！").toJsonString();
            path.append(pictureSuffixEnum.getCatalog());
            path.append("/");
            path.append(uuid);
            path.append(fileSuffix);
            //创建临时储存--走第三方储存无需本地存储
            toFile = File.createTempFile("tmp", null);
            file.transferTo(toFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(limingConfig.getBucketName(), path.toString(), toFile);
            CosClientUtil.uploadDocument(putObjectRequest);
            //删除临时储存
            toFile.deleteOnExit();
            return Result.success("上传成功",path.toString()).toJsonString();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.toString()).toJsonString();
        }
    }

    @GetMapping("/deleteDocument")
    public String deleteDocument(@RequestParam(value = "documentPath",required = true) String documentPath){
        String bucketName = limingConfig.getBucketName();
        String key = documentPath;
        CosClientUtil.deleteDocument(bucketName,key);
        return Result.success().toJsonString();
    };
}
