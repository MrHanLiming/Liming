package com.liming.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties(LimingConfig.class)
@ConfigurationProperties("liming")
public class LimingConfig {

    private String bucketName;

    private String whiteRoster;

    private boolean isCheckIp;

}
