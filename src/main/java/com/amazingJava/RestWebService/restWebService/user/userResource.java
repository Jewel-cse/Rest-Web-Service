package com.amazingJava.RestWebService.restWebService.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class userResource {
    private userDaoService service;
    public userResource(userDaoService service){
        this.service = service;
    }

    // ################# GET #####################

    //http://localhost:8080/users
    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return service.findAll();
    }

    //implement hateoas
    //EntityModel
    //WebMvcLinkBuilder
    //http://localhost:8080/users/2
    @GetMapping("/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id){
        User user= service.findOne(id);
        //System.out.println("Where are you my fucking error "+user);
        if(user == null){
            throw new UserNotFoundException("id :"+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return  entityModel;
    }


    // ################## POST ##############

    //http://localhost:8080/users
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()  //users
                .path("/{id}")     //add path variable
                .buildAndExpand(savedUser.getId()) //replace with id : / users/id
                .toUri();           //create uri
        return ResponseEntity.created(location).build();
    }

    //############# Delete ################

    //http:localhost:8080/users/{id}
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }

}
