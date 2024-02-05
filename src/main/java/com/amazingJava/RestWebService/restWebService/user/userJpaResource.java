package com.amazingJava.RestWebService.restWebService.user;

import com.amazingJava.RestWebService.restWebService.jpa.postRepository;
import com.amazingJava.RestWebService.restWebService.jpa.userRepository;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class userJpaResource {
    private userRepository repository;
    private postRepository postRepository;

    public userJpaResource(userRepository repository ,postRepository postRepository){
        this.postRepository = postRepository;
        this.repository = repository;
    }

    // ################# GET #####################

    //http://localhost:8080/jpa/users
    @GetMapping("/jpa/users")
    public List<User> retriveAllUsers(){
        return repository.findAll();
    }

    //implement hateoas
    //EntityModel
    //WebMvcLinkBuilder
    //http://localhost:8080/jpa/users/2
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id){
        Optional<User> user= repository.findById(id);
        //System.out.println("Where are you my fucking error "+user);
        if(user.isEmpty()){
            throw new UserNotFoundException("id :"+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return  entityModel;
    }


    // ################## POST ##############

    //http://localhost:8080/jpa/users
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = (User) repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()  //users
                .path("/{id}")     //add path variable
                .buildAndExpand(savedUser.getId()) //replace with id : / users/id
                .toUri();           //create uri
        return ResponseEntity.created(location).build();
    }

    //############# Delete ################

    //http:localhost:8080/jpa/users/{id}
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);
    }

    //########## retrive all posts for a user #######

    //http://localhost:8080/jpa/users/1001/posts
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retriveAllPostForAUser(@PathVariable int id){
        Optional<User> user= repository.findById(id);
        //System.out.println("Where are you my fucking error "+user);
        if(user.isEmpty()){
            throw new UserNotFoundException("id :"+id);
        }
        return  user.get().getPosts();
    }

    //########### Create a post for a specific user

    //http://localhost:8080/jpa/users/{id}/posts
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForAUser(@PathVariable int id,@Valid @RequestBody Post post ){

        Optional<User> user = repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id :"+id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()  //users
                .path("/{id}")     //add path variable
                .buildAndExpand(savedPost.getId()) //replace with id : / users/id
                .toUri();           //create uri
        return ResponseEntity.created(location).build();

    }


}
