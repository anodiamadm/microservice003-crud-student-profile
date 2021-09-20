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
    @GetMapping("/profile")
    @ResponseBody
    public StudentProfile getStudentProfileInfo() throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                if(currentUserName!=null) {
                    Optional<User> optionalUser = userService.findByUsername(currentUserName);
                    if(optionalUser.isPresent() && optionalUser.get().getStudentProfile().getStudentProfileId()!=null) {
                        StudentProfile returnedStudentProfile = optionalUser.get().getStudentProfile();
                        messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(),
                                "Required Student Profile Found!");
                        returnedStudentProfile.setMessageResponse(messageResponse);
                        return returnedStudentProfile;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentProfile studentProfile = new StudentProfile();
        messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),
                "Student Profile Not Found!");
        studentProfile.setMessageResponse(messageResponse);
        return studentProfile;
    }

    //  @PostMapping("save-profile") :: Fetch logged-in user's profile Info
    @PostMapping("/save-profile")
    @ResponseBody
    public ResponseEntity<?> saveStudentProfileInfo(@Valid @RequestBody StudentProfile studentProfile)
            throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Student Profile not saved!");
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                if(currentUserName!=null) {
                    Optional<User> optionalUser = userService.findByUsername(currentUserName);
                    if(optionalUser.isPresent()) {
                        StudentProfile savedStudentProfile = studentProfileService.save(studentProfile);
                        return ResponseEntity.ok(new MessageResponse(savedStudentProfile.getMessageResponse().getCode(),
                                savedStudentProfile.getMessageResponse().getMessage()));
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse.setMessage(exception.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),
                messageResponse.getMessage()));
    }

    //  @PostMapping("modify-profile") :: Fetch logged-in user's profile Info
    @PostMapping("/modify-profile")
    @ResponseBody
    public ResponseEntity<?> modifyStudentProfileInfo(@Valid @RequestBody StudentProfile studentProfile)
            throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Student Profile not modified!");
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                if(currentUserName!=null) {
                    Optional<User> optionalUser = userService.findByUsername(currentUserName);
                    if(optionalUser.isPresent() && studentProfileService.findById(studentProfile.getStudentProfileId())!=null) {
                        StudentProfile savedStudentProfile = studentProfileService.modify(studentProfile);
                        return ResponseEntity.ok(new MessageResponse(savedStudentProfile.getMessageResponse().getCode(),
                                savedStudentProfile.getMessageResponse().getMessage()));
                    }
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
