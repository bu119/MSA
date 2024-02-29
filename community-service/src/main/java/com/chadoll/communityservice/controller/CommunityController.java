package com.chadoll.communityservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController {

    @GetMapping("/name")
    public String getName(){
        return "커뮤니티";
    }
}
