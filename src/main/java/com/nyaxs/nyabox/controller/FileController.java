package com.nyaxs.nyabox.controller;

import com.nyaxs.nyabox.entity.Files;
import com.nyaxs.nyabox.entity.UploadFileResponse;
import com.nyaxs.nyabox.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/fileList")
    public String getFileList(){
        List<Files> filesList = new ArrayList<>();
        File file = new File(fileService.getFileStorageLocation().toUri());
        File[] list = file.listFiles();
        return list.toString();
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file){
        String fileName = fileService.storeFile(file);
        String downUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(fileName)
                .toUriString();
        UploadFileResponse response = new UploadFileResponse(fileName,downUri,file.getContentType(),file.getSize());
        return response;
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMutipleFiles(@RequestParam("files") MultipartFile[] files){
        return Arrays.stream(files).map(this::uploadFile)
                .collect(Collectors.toList());
    }


    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downFile(@PathVariable String fielName, HttpServletRequest request){
        Resource resource = fileService.loadFileAsResource(fielName);
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException exception){
            log.info("Could not determine file type.");
        }

        // 无法获取文件类型，则设为默认
        if (contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

}
