package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user;

import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class UserServiceDal extends UserServiceImpl {

    @Autowired
    private UserService userService;

    public UserServiceDal(){}

    @Override
    public Optional<User> findByUsername(String username) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Optional<User> isUserPresent = userService.findByUsername(username);
            if(isUserPresent.isPresent()) {
                messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(), "User Exists by Username: " + username + "!");
                return ;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return userService.findByUsername(username);
    }
}
