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

    // This method will retrieve Current User from token
    private User getCurrentUser()
    {
        try
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken))
            {
                System.out.println("User name: " + auth.getName());
                return userService.findByUsername(auth.getName()).get();
            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    //  @GetMapping("/profile") :: List Profile Info of the Current Logged-in User:
    @GetMapping("/profile")
    @ResponseBody
    public Optional<StudentProfile> getStudentProfileInfo() throws Exception
    {
        try
        {
            return studentProfileService.findByUser(getCurrentUser());
        } catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
     }

    // @PostMapping("/save-profile") :: Save Profile Info of the Current Logged-in User
    @PostMapping("/save-profile")
    @ResponseBody
    public ResponseEntity<?> saveStudentProfileInfo(@Valid @RequestBody StudentProfile studentProfile) throws Exception
    {
        try
        {
            studentProfile.setUser(getCurrentUser());
            StudentProfile studentProfileToSave = studentProfileService.save(studentProfile);
            return ResponseEntity.ok(studentProfileToSave.getMessageResponse());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),exception.getMessage()));
        }
    }

//  @PostMapping("/modify-profile") :: Modify Profile Info of the Current Logged-in User
    @PostMapping("/modify-profile")
    @ResponseBody
    public ResponseEntity<?> modifyStudentProfileInfo(@Valid @RequestBody StudentProfile studentProfile) throws Exception
    {
        try
        {
            studentProfile.setUser(getCurrentUser());
            StudentProfile studentProfileToSave = studentProfileService.modify(studentProfile);
            return ResponseEntity.ok(studentProfileToSave.getMessageResponse());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(),exception.getMessage()));
        }
    }
}
