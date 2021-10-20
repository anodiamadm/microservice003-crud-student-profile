package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
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

   /* //  @PostMapping("/save-profile") :: Save Profile Info of the Current Logged-in User
    @PostMapping("/save-profile")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> saveStudentProfileInfo(@Valid @RequestBody User user) throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                User currentUser = userService.findByUsername(currentUserName).get();
                if(currentUser!=null && currentUser.getStudentProfile()==null && user.getStudentProfile()!=null) {
                    StudentProfile studentProfileToSave = studentProfileService.save(user.getStudentProfile());
                    if(studentProfileToSave.getMessageResponse().getCode()==ResponseCode.SUCCESS.getID()) {
                        currentUser.setStudentProfile(studentProfileToSave);
                        userService.save(currentUser);
                        return ResponseEntity.ok(new MessageResponse(ResponseCode.SUCCESS.getID(), "User Profile SAVED!"));
                    } else { messageResponse.setMessage(studentProfileToSave.getMessageResponse().getMessage());}
                } else { messageResponse.setMessage("Invalid User or StudentProfile!"); }
            } else { messageResponse.setMessage("Invalid Authentication Token!"); }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse.setMessage(exception.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(), messageResponse.getMessage()));
    }*/

    /*//  @PostMapping("/modify-profile") :: Modify Profile Info of the Current Logged-in User
    @PostMapping("/modify-profile")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> modifyStudentProfileInfo(@Valid @RequestBody User user) throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                String currentUserName = auth.getName();
                User currentUser = userService.findByUsername(currentUserName).get();
                if(currentUser!=null && currentUser.getStudentProfile()!=null && user.getStudentProfile()!=null) {
                    BigInteger studentProfileIDToBeUpdated = currentUser.getStudentProfile().getStudentProfileId();
                    currentUser.setStudentProfile(null);
                    User userToModify = userService.save(currentUser);
                    if(userToModify.getMessageResponse().getCode()==ResponseCode.SUCCESS.getID()) {
                        messageResponse = studentProfileService.removeOne(studentProfileIDToBeUpdated);
                        if (messageResponse.getCode() == ResponseCode.SUCCESS.getID()) {
                            StudentProfile studentProfileToSave = studentProfileService.save(user.getStudentProfile());
                            if(studentProfileToSave.getMessageResponse().getCode()==ResponseCode.SUCCESS.getID()) {
                                currentUser.setStudentProfile(studentProfileToSave);
                                userService.save(currentUser);
                                return ResponseEntity.ok(new MessageResponse(ResponseCode.SUCCESS.getID(), "User Profile Updated!"));
                            } else { messageResponse.setMessage(studentProfileToSave.getMessageResponse().getMessage());}                        }
                    } else { messageResponse.setMessage(userToModify.getMessageResponse().getMessage()); }
                } else { messageResponse.setMessage("Invalid User or StudentProfile!"); }
            } else { messageResponse.setMessage("Invalid Authentication Token!"); }
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse.setMessage(exception.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse(ResponseCode.FAILURE.getID(), messageResponse.getMessage()));
    }*/
}
