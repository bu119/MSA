package com.chadoll.apigatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApigatewayController {

    @GetMapping("/name")
    public String getName(){
        return "API 게이트웨이";
    }

}
