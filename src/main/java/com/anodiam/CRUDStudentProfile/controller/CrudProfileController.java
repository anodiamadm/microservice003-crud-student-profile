package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.db.repository.*;
import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class CrudProfileController {

    private UserRepository userRepository;
    private StudentProfileRepository studentProfileRepository;

    public CrudProfileController(UserRepository userRepository,
                                 StudentProfileRepository studentProfileRepository) {
        this.userRepository = userRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

//  REVIEW COMMENT::  TRY - CATCH needs to narrow down to specific exception instead of just EXCEPTION
//  @GetMapping("profile") :: Fetch logged-in user's profile Info
    @GetMapping("profile")
    @ResponseBody
    public StudentProfile getStudentProfileInfo() throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                if(currentUserName!=null) {
                    BigInteger studentProfileId = userRepository.findByUsername(currentUserName)
                                                                .getStudentProfile()
                                                                .getStudentProfileId();
                    if(studentProfileId!=null) {
                        StudentProfile studentProfile = studentProfileRepository
                                                            .findByStudentProfileId(studentProfileId);
                        if(studentProfileId!=null) {
                            return studentProfile;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
