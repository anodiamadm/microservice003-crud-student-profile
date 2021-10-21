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

    //  @GetMapping("/profile") :: List Profile Info of the Current Logged-in User:
    @GetMapping("/profile")
    @ResponseBody
    public Optional<StudentProfile> getStudentProfileInfo() throws Exception {
        User user = new User();
        MessageResponse messageResponse = new MessageResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                if(currentUserName!=null) {
                    User returnedUser = userService.findByUsername(currentUserName).get();
                    Optional<StudentProfile> returnedStudentProfile =
                            studentProfileService.findByUser(returnedUser);
                    return returnedStudentProfile;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(), exception.getMessage());
            user.setMessageResponse(messageResponse);
        }
        return null;
    }

//  @PostMapping("/save-profile") :: Save Profile Info of the Current Logged-in User
    @PostMapping("/save-profile")
    @ResponseBody
    public ResponseEntity<?> saveStudentProfileInfo(@Valid @RequestBody StudentProfile studentProfile) throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                User currentUser = userService.findByUsername(currentUserName).get();
                if(currentUser!=null ) {
//      Need to also check if current user does NOT have an existing Student Profile.
//      Then only save, otherwise raise error asking
//      ELSE - Student profile already exists SAVE not possible, only update/modify is possible.
                    studentProfile.setUser(currentUser);
                    StudentProfile studentProfileToSave = studentProfileService.save(studentProfile);
                    return ResponseEntity.ok(studentProfileToSave.getMessageResponse());
                }// ELSE - Student profile already exists SAVE not possible, only update/modify is possible.
            } else { messageResponse.setMessage("Invalid Authentication Token!"); }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse.setMessage(exception.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(), messageResponse.getMessage()));
    }

//  @PostMapping("/modify-profile") :: Modify Profile Info of the Current Logged-in User
    @PostMapping("/modify-profile")
    @ResponseBody
    public ResponseEntity<?> modifyStudentProfileInfo(@Valid @RequestBody StudentProfile studentProfile) throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                User currentUser = userService.findByUsername(currentUserName).get();
                if(currentUser!=null) {
//      Need to also check if current user does have an existing Student Profile.
//      Then only Modify, otherwise raise error asking
//      ELSE - Student profile does not exist, only save is possible. Modify is not possible.
                    studentProfile.setUser(currentUser);
                    StudentProfile studentProfileToSave = studentProfileService.modify(studentProfile);
                    return ResponseEntity.ok(studentProfileToSave.getMessageResponse());
                }// ELSE - Student profile does not exist, only save is possible. Modify is not possible.
            } else { messageResponse.setMessage("Invalid Authentication Token!"); }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse.setMessage(exception.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(), messageResponse.getMessage()));
    }
}
