package com.demo.demo.serviceImpl;

import org.springframework.stereotype.Service;

//@Profile("/dev")
@Service
public class ProfileServiceImpl {

    public String welcomeQA() {
        return " Welcome qa";
    }

    public String welcomeDEV() {
        return " Welcome dev";
    }
}
