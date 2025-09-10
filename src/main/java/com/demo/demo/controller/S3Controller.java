package com.demo.demo.controller;

import com.demo.demo.dto.FileUploadResponseDto;
import com.demo.demo.serviceImpl.s3.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v2/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponseDto> uploadFile(MultipartFile file) throws Exception {
        return ResponseEntity.ok(s3Service.uploadFile(file));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) throws Exception {
        return ResponseEntity.ok(s3Service.downloadFile(fileName));
    }

    @GetMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFileName(@PathVariable("fileName") String fileName) throws Exception {
        return ResponseEntity.ok(s3Service.deleteFile(fileName));
    }

    @GetMapping("/url/download/{fileName}")
    public ResponseEntity<String> getUrlOfObject(@PathVariable("fileName") String fileName) throws Exception {
        return ResponseEntity.ok(s3Service.getUrlOfObject(fileName));
    }

    @GetMapping("/url/download/get/")
    public ResponseEntity<List<String>> getAllObject() throws Exception {
        return ResponseEntity.ok(s3Service.getAllObject());
    }
}
