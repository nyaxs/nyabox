package com.nyaxs.nyabox.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName FileProperties
 * @Description TODO:
 * @Author Administrator
 * @Date 2020-08-27 9:23
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;
}
