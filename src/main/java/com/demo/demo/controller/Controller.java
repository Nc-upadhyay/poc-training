package com.demo.demo.controller;

import com.demo.demo.dto.StudentDto;
import com.demo.demo.serviceImpl.AsyncDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private AsyncDemo asyncDemo;


    @GetMapping("/async")
    public StudentDto asyncTask(){
//        for (int i=0;i<5;i++) {
           return asyncDemo.executeAsyncTask();
//        }

//        return " Welcome ";
    }

    @RequestMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Testing interceptor");
    }

}
