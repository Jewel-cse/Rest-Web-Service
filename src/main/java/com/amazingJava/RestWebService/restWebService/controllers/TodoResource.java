package com.amazingJava.RestWebService.restWebService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TodoResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final List<Todo> TODO_LIST = List.of(new Todo("jewel", "learn AWS"),
            new Todo("jewel", "Get DevOps"));

    @GetMapping("/todos")
    public  List<Todo> retrieveAllTodos(){
        return TODO_LIST;
    }

    @GetMapping("/users/{username}/todos")
    public  Todo retrieveTodosforspecificUser(@PathVariable String username){
        return TODO_LIST.get(0);
    }

    @PostMapping("/users/{username}/todos")
    public void createTodos(@PathVariable String username,@RequestBody Todo todo){
        logger.info("creating {} for {}",todo,username);
    }

}

record Todo(String username,String description){}