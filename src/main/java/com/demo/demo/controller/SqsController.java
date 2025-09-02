package com.demo.demo.controller;

import com.demo.demo.dto.sqs.Message;
import com.demo.demo.serviceImpl.sqs.ProducerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    @Autowired
    private ProducerImpl producer;

    @GetMapping("/send")
    public ResponseEntity<?> sendMesToQueue(@RequestParam(required = false) String id, @RequestParam(required = false) String msg) {
        for(int i=5;i<15;i++)
            producer.sendMassage(i+"",msg+" "+i);
        return ResponseEntity.ok(producer.sendMassage(id, msg));
    }
}
