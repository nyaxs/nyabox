package com.nyaxs.nyabox.entity;

import lombok.Data;

/**
 * @ClassName Files
 * @Description TODO:
 * @Author Administrator
 * @Date 2020-08-26 17:28
 * @Version 1.0
 **/
@Data
public class Files {
    private String name;
    private String type;
    private String createTime;
    private String uploadTime;
    private Integer downNumber;
    private String ownerName;
    private Long id;
    private String address;
    private Integer size;

}
