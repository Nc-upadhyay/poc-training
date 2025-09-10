package com.demo.demo.serviceImpl.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.demo.demo.config.S3Config;
import com.demo.demo.dto.FileUploadResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class S3Service {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3Client;

    public FileUploadResponseDto uploadFile(MultipartFile multipartFile) throws Exception {
        log.info("bucketName =>> {} "+bucketName);
        FileUploadResponseDto fileUploadResponseDto=new FileUploadResponseDto();
        try{
            ObjectMetadata objectMetadata=new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            String filPath=multipartFile.getOriginalFilename();
            amazonS3Client.putObject(bucketName,filPath,multipartFile.getInputStream(),objectMetadata);
            fileUploadResponseDto.setFilePath(filPath);

        }catch (Exception e){
            log.info("error occurred=>> {}  "+e.getMessage());
            throw new Exception(" Error occurred in file upload... "+e.getMessage());
        }
        return  fileUploadResponseDto;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object=amazonS3Client.getObject(bucketName,fileName);
        S3ObjectInputStream s3ObjectInputStream=s3Object.getObjectContent();
        try{
            return IOUtils.toByteArray(s3ObjectInputStream);
        }catch (Exception e){
            return  null;
        }

    }

    public String deleteFile(String fileName) {
        try {
            amazonS3Client.deleteObject(bucketName,fileName);
            return "file delete";
        }catch (Exception e){
            return "File not delete";
        }

    }

    public String getUrlOfObject(String fileName) {
        try {
            return amazonS3Client.getUrl(bucketName,fileName).toString();
        }catch (Exception e){
            return " Url not generated... ";
        }
    }

    public List<String> getAllObject() {
        List<String> list=new ArrayList<>();
        ObjectListing objectListing=amazonS3Client.listObjects(bucketName);
        for(S3ObjectSummary objectSummary:objectListing.getObjectSummaries()){
            URL url=amazonS3Client.generatePresignedUrl(bucketName,objectSummary.getKey(),new Date(System.currentTimeMillis()+1000*60*10));
            list.add(url.toString());
        }
        return list;
    }
}
