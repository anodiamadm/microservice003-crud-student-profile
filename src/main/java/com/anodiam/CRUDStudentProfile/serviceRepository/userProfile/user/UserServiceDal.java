package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user;

import com.anodiam.CRUDStudentProfile.model.User;
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

        try {
            Optional<User> optionalUser = userService.findByUsername(username);
            if(optionalUser.isPresent()) {
                return optionalUser;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
