package com.liming.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;


public class CosClientUtil {

    private static String secretId = "AKIDMVRyrVz2vXtUpuETr2oLdgYAesrZRQXQ";

    private static String secretKey = "nKbeS8bpnV3Np5HF0ZyHOL5dEftqZ4LR";

    private static String regionName = "ap-shanghai";

    private static COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

    private static ClientConfig clientConfig = new ClientConfig(new Region(regionName));

    public static void uploadDocument(PutObjectRequest putObjectRequest){
        // 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        try {
            cosclient.putObject(putObjectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }

    public static void deleteDocument(String bucketName,String key){
        COSClient cosclient = new COSClient(cred, clientConfig);
        try {
            cosclient.deleteObject(bucketName, key);
        }finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }

}
