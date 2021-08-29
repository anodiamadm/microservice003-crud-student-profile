package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.db.repository.UserRepository;
import com.anodiam.CRUDStudentProfile.model.User;
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

    //    Available to all authenticated users
    @GetMapping("user/profile")
    public List<User> users(){
        return this.userRepository.findAll();
    }
}
