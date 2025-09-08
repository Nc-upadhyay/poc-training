package com.demo.demo.controller.mongo;

import com.demo.demo.collections.Address;
import com.demo.demo.collections.StudentCollections;
import com.demo.demo.serviceImpl.mongo.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody StudentCollections studentCollections){
        return ResponseEntity.status(201).body(studentService.save(studentCollections));
    }

    @PostMapping("/save/all")
    public ResponseEntity<?> saveAll(@RequestBody List<StudentCollections> studentCollections){
        return ResponseEntity.status(201).body(studentService.saveAll(studentCollections));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getById(@RequestParam(required = false) ObjectId id) throws JsonProcessingException {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<?> getById(@RequestBody StudentCollections studentCollections){
        return ResponseEntity.ok(studentService.updateById(studentCollections));
    }


    @GetMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam ObjectId id){
        return ResponseEntity.ok(studentService.deleteById(id));
    }

    @PostMapping("/save/address")
    public ResponseEntity<?> saveByAddress(@RequestBody Address address,@RequestParam(required = false) ObjectId id) throws Exception {
        return ResponseEntity.ok(studentService.saveByAddress(address,id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getByAge(@RequestParam(required = false) int age) throws Exception {
        return ResponseEntity.ok(studentService.getAllStudentByAge(age));
    }

    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam(required = false) String  name,@RequestParam(required = false) String  name2) {
        return ResponseEntity.ok(studentService.getByName(name,name2));
    }
}
