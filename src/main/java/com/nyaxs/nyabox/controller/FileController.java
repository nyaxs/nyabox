package com.nyaxs.nyabox.controller;

import com.nyaxs.nyabox.entity.Files;
import com.nyaxs.nyabox.entity.UploadFileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FileController
 * @Description TODO:
 * @Author Administrator
 * @Date 2020-08-26 17:18
 * @Version 1.0
 **/
@RestController
@Slf4j
public class FileController {

    private static final String PATH = "";

    @GetMapping("/fileList")
    public List<Files> getFileList(){
        List<Files> filesList = new ArrayList<>();
        File file = new File(PATH);
        File[] list = file.listFiles();
        return filesList;
    }

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file){
        return null;
    }

    @GetMapping("/download")
    public void downFile(){

    }

}
