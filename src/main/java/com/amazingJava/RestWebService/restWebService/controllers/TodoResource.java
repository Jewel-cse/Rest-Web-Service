package com.amazingJava.RestWebService.restWebService.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TodoResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final List<Todo> TODO_LIST = List.of(new Todo("jewel1", "learn AWS"),
            new Todo("jewel", "Get DevOps"));

    @GetMapping("/todos")
    public  List<Todo> retrieveAllTodos(){
        return TODO_LIST;
    }

    @GetMapping("/users/{username}/todos")
    @PreAuthorize("hasRole('USER')and #username == authentication.name")     //at first auth.username er sathe pathvariable username check korbe
    @PostAuthorize("returnObject.username == 'jewel1'")         //return object er username er sathe check korbe
    @RolesAllowed({"ADMIN","USER"})
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public  Todo retrieveTodosforspecificUser(@PathVariable String username){
        return TODO_LIST.get(0);
    }

    @PostMapping("/users/{username}/todos")
    public void createTodos(@PathVariable String username,@RequestBody Todo todo){
        logger.info("creating {} for {}",todo,username);
    }

}

record Todo(String username,String description){}