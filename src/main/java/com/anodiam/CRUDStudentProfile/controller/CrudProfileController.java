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

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CrudProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentProfileService studentProfileService;

    // This method will retrieve Current User from token
    private User getCurrentUser()
    {
        try
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken))
            {
               return userService.findByUsername(auth.getName()).get();
            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    // @PostMapping("/save-profile") :: Create / Update Profile Info of the Current Logged-in User
    @PostMapping("/save-profile")
    @ResponseBody
    public ResponseEntity<?> createStudentProfile(@RequestBody StudentProfile studentProfile) throws Exception {
        try {
            studentProfile.setUser(getCurrentUser());
            StudentProfile studentProfileSaved = studentProfileService.save(studentProfile);
            return ResponseEntity.ok(new MessageResponse(studentProfileSaved.getMessageResponse().getCode(),
                    studentProfileSaved.getMessageResponse().getMessage()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),
                    exception.getMessage()));
        }
    }

    // @GetMapping("/read-profile") :: Read Profile Info of the Current Logged-in User
    @GetMapping("/read-profile")
    @ResponseBody
    public StudentProfile readStudentProfile() throws Exception
    {
        try {
            User currentUser = getCurrentUser();
            return studentProfileService.findByUserId(currentUser.getUserId()).get();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
     }

    // @PostMapping("/delete-profile") :: Delete Profile Info of the Current Logged-in User
    @PostMapping("/delete-profile")
    @ResponseBody
    public ResponseEntity<?> deleteStudentProfile(@RequestBody StudentProfile studentProfile) throws Exception {
        try {
            StudentProfile currentStudentProfile = studentProfileService.findByUserId(getCurrentUser().getUserId()).get();
            MessageResponse responseToProfileDelete = studentProfileService.removeOne(currentStudentProfile.getStudentProfileId());
            return ResponseEntity.ok(new MessageResponse(responseToProfileDelete.getCode(),
                    responseToProfileDelete.getMessage()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),
                    exception.getMessage()));
        }
    }
}