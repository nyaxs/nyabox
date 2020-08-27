package com.nyaxs.nyabox.service;

import com.nyaxs.nyabox.common.FileException;
import com.nyaxs.nyabox.entity.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @ClassName FileService
 * @Description TODO:
 * @Author Administrator
 * @Date 2020-08-27 9:53
 * @Version 1.0
 **/
@Service
public class FileService {
    //文件存储到本地的地址
    private final Path fileStorageLocation ;

    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception exception){
            throw new FileException("Could not create the directory where the uploaded files will be stored.",exception);
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = file.getOriginalFilename();

        try{
            if(fileName.contains("..")){
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        }catch (IOException ex){
            throw new FileException("Could not store file " + fileName + ". Check and Try Again!");
        }
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else {
                throw new FileException("File Not Found " + fileName);
            }

        }catch (Exception exception){
            throw new FileException("File Not Found " + fileName, exception);
        }

    }


}
