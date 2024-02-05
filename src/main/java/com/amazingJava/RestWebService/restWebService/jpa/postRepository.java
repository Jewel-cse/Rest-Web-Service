package com.amazingJava.RestWebService.restWebService.jpa;

import com.amazingJava.RestWebService.restWebService.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<Post,Integer> {

}
