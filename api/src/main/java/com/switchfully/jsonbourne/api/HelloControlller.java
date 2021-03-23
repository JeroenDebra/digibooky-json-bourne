package com.switchfully.jsonbourne.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/")
public class HelloControlller {

    @GetMapping
    public String Hello(){
        return "Hello World!";
    }

}