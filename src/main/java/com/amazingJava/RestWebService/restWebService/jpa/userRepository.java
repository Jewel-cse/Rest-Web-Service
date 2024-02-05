package com.amazingJava.RestWebService.restWebService.jpa;

import com.amazingJava.RestWebService.restWebService.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository  extends JpaRepository<User,Integer> {

}
