package com.liming.bean;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CosClientBean {

    @Value(value = "${liming.secretId}")
    private String secretId;

    @Value(value = "${liming.secretKey}")
    private String secretKey;

    @Value(value = "${liming.regionName}")
    private String regionName;

    @Bean
    public synchronized COSClient cosClient(){
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

}
