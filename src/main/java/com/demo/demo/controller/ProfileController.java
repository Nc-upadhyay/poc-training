package com.demo.demo.controller;

import com.demo.demo.serviceImpl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileService;


    @GetMapping("/qa")
    public String getQAProfile(){
        return profileService.welcomeQA();
    }

    @GetMapping("/dev")
    public String  getDEVProfile(){
        return profileService.welcomeDEV();
    }
}
