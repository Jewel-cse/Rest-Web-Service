package com.amazingJava.RestWebService.restWebService.helloWorldController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorldResource {
    @GetMapping("/hello-world")
    public  String helloWorld(){
        return  "hello world -v1";
    }
}
