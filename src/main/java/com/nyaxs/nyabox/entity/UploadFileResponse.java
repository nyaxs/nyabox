package com.nyaxs.nyabox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UploadFileResponse
 * @Description TODO:
 * @Author Administrator
 * @Date 2020-08-27 9:30
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {
    private String fileName;
    private String fileDownLoadUri;
    private String fileType;
    private Long size;
}
