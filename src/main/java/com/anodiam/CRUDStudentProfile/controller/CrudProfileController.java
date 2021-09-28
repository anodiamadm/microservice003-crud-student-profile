package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile.StudentProfileService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CrudProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentProfileService studentProfileService;

    //  @GetMapping("/profile") :: List Profile Info of the Current Logged-in User:
    @GetMapping("/profile")
    @ResponseBody
    public User getStudentProfileInfo() throws Exception {
        User user = new User();
        MessageResponse messageResponse = new MessageResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                if(currentUserName!=null) {
                    User returnedUser = userService.findByUsername(currentUserName).get();
                    return returnedUser;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(), exception.getMessage());
            user.setMessageResponse(messageResponse);
        }
        return user;
    }

    //  @PostMapping("/save-profile") :: Save Profile Info of the Current Logged-in User
    @PostMapping("/save-profile")
    @ResponseBody
    public ResponseEntity<?> saveStudentProfileInfo(@Valid @RequestBody User user)
            throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Invalid User: Student profile already exists!");
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                User currentUser = userService.findByUsername(currentUserName).get();
                if(currentUserName!=null && currentUser.getStudentProfile()==null) {
                    StudentProfile savedStudentProfile = studentProfileService.save(user.getStudentProfile());
                    currentUser.setStudentProfile(savedStudentProfile);
                    userService.save(currentUser);
                    return ResponseEntity.ok(new MessageResponse(ResponseCode.SUCCESS.getID(),
                            "User SAVED!"));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse.setMessage(exception.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),
                messageResponse.getMessage()));
    }

    //  @PostMapping("/save-profile") :: Modify Profile Info of the Current Logged-in User
    @PostMapping("/modify-profile")
    @ResponseBody
    public ResponseEntity<?> modifyStudentProfileInfo(@Valid @RequestBody User user)
            throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Invalid User: Student profile Does Not exists!");
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                User currentUser = userService.findByUsername(currentUserName).get();

//                BUG HERE
                if(currentUserName!=null && currentUser.getStudentProfile()!=null) {
                    StudentProfile savedStudentProfile = studentProfileService.save(user.getStudentProfile());
                    currentUser.setStudentProfile(savedStudentProfile);
                    userService.save(currentUser);
                    return ResponseEntity.ok(new MessageResponse(ResponseCode.SUCCESS.getID(),
                            "User MODIFIED!"));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse.setMessage(exception.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),
                messageResponse.getMessage()));
    }
}
