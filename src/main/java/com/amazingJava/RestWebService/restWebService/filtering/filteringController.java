package com.amazingJava.RestWebService.restWebService.filtering;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController

public class filteringController {

    @GetMapping("/filtering")
    public SomeBean filtering(){
        return new SomeBean("value1","value2","Value3");
    }
    @GetMapping("/filtering-list")
    public List<SomeBean> filteringList(){
        return Arrays.asList(new SomeBean("value1","value2","Value3"),
                new SomeBean("value4","value5","Value6")) ;
    }
}
