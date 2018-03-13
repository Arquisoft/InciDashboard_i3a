package com.uniovi.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.UserInfo;

@RestController
public class APIController {

    @RequestMapping("/user")
    public UserInfo user() {
        return new UserInfo("pepe",0);
    }

}