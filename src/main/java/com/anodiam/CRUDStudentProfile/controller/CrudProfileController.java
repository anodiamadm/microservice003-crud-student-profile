package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile.StudentProfileService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CrudProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentProfileService studentProfileService;

//  @GetMapping("profile") :: Fetch logged-in user's profile Info
    @GetMapping("profile")
    @ResponseBody
    public StudentProfile getStudentProfileInfo() throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                if(currentUserName!=null) {
                    Optional<User> optionalUser = userService.findByUsername(currentUserName);

//                                                                .getStudentProfile()
//                                                                .getStudentProfileId();
//                    if(studentProfileId!=null) {
//                        StudentProfile studentProfile = studentProfileRepository
//                                                            .findByStudentProfileId(studentProfileId);
//                        if(studentProfileId!=null) {
//                            return studentProfile;
//                        }
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
