package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user;

import com.anodiam.CRUDStudentProfile.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceDal extends UserServiceImpl {

    @Autowired
    private UserService userService;

    public UserServiceDal(){}

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            Optional<User> isUserPresent = userService.findByUsername(username);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return userRepository.findByUsername(username);
    }
}
