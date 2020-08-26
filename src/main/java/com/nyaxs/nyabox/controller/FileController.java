package com.nyaxs.nyabox.controller;

import com.nyaxs.nyabox.entity.Files;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public int uploadFile(@RequestBody Files file){
        return 1;
    }

    @GetMapping("/download")
    public void downFile(){

    }

}
