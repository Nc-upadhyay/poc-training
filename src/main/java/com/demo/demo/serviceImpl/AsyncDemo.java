package com.demo.demo.serviceImpl;

import com.demo.demo.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncDemo {

    Logger logger= LoggerFactory.getLogger(AsyncDemo.class);


    public StudentDto executeAsyncTask() {
        System.out.println("Contoller :" + Thread.currentThread().getName());
        task();
        return new StudentDto("name",123456789);
    }

    @Async
    public void task() {
        System.out.println("Thread : " + Thread.currentThread().getName());
    }
}
