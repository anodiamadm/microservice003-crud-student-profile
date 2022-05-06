package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board.BoardService;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level.LevelService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile.StudentProfileService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CrudProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentProfileService studentProfileService;

    // This method will retrieve Current User from token
    private Optional<User> getCurrentUser() {
        User returnedUser = new User();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                returnedUser = userService.findByUsername(auth.getName()).get();
                returnedUser.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(),
                        ResponseCode.SUCCESS.getMessage()));
            } else {
                returnedUser.setMessageResponse(new MessageResponse(ResponseCode.INVALID_CURRENT_USER.getID(),
                        ResponseCode.INVALID_CURRENT_USER.getMessage()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            returnedUser.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),
                    ResponseCode.FAILURE.getMessage()));
        }
        return Optional.of(returnedUser);
    }

//  @PostMapping("/save-profile"): Create / Update Profile Info of the Current Logged-in User
    @PostMapping("/save-profile")
    @ResponseBody
    public ResponseEntity<?> createStudentProfile(@RequestBody StudentProfile studentProfile) throws Exception {
        try {
            Optional<User> currentUser = getCurrentUser();
            if(currentUser.isPresent()){
                studentProfile.setUser(currentUser.get());
                MessageResponse responseToProfileSave =
                        studentProfileService.save(studentProfile);
                return ResponseEntity.ok(new MessageResponse(responseToProfileSave.getCode(),
                        responseToProfileSave.getMessage()));
            } else {
                return ResponseEntity.ok(currentUser.get().getMessageResponse());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),
                    exception.getMessage()));
        }
    }

//  @GetMapping("/read-profile"): Read Profile Info of the Current Logged-in User
    @GetMapping("/read-profile")
    @ResponseBody
    public StudentProfile readStudentProfile() throws Exception {
        StudentProfile returnedProfile = new StudentProfile();
        try {
            Optional<User> currentUser = getCurrentUser();
            if(currentUser.isPresent()){
                returnedProfile = studentProfileService.findByUser(currentUser.get()).get();
            } else {
                returnedProfile.setMessageResponse(currentUser.get().getMessageResponse());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            returnedProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),
                    ResponseCode.FAILURE.getMessage() + exception.getMessage()));
        }
        return returnedProfile;
    }

//  @PostMapping("/delete-profile"): Delete Profile Info of the Current Logged-in User
    @PostMapping("/delete-profile")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> deleteStudentProfile() throws Exception {
        try {
            Optional<User> currentUser = getCurrentUser();
            if(currentUser.isPresent()){
                MessageResponse responseToProfileDelete = studentProfileService.deleteByUser(currentUser.get());
                return ResponseEntity.ok(responseToProfileDelete);
            } else {
                return ResponseEntity.ok(currentUser.get().getMessageResponse());
            }
        } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),
                ResponseCode.FAILURE.getMessage() + exception.getMessage()));
        }
    }
}