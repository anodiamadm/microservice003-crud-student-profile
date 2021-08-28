package com.anodiam.JWTAuth.controller;

import com.anodiam.JWTAuth.db.repository.UserRepository;
import com.anodiam.JWTAuth.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class PublicRestApiController {

    private UserRepository userRepository;
    public PublicRestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    Available to ROLE_ADMIN
    @GetMapping("user/profile")
    public List<User> users(){
        return this.userRepository.findAll();
    }
}
