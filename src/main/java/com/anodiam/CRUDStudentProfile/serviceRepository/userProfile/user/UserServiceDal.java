package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user;

import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class UserServiceDal extends UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public UserServiceDal(){}

    @Override
    public Optional<User> findByUsername(String username) {
        User userReturned = new User();
        try {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if(optionalUser.isPresent()) {
                userReturned = optionalUser.get();
                userReturned.setMessageResponse(new
                        MessageResponse(ResponseCode.USER_EXISTS.getID(),
                        ResponseCode.USER_EXISTS.getMessage()
                                + userReturned.getUsername()));
            } else {
                userReturned.setMessageResponse(new
                        MessageResponse(ResponseCode.USER_ABSENT.getID(),
                        ResponseCode.USER_ABSENT.getMessage()
                                + username));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            userReturned.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),
                    ResponseCode.FAILURE.getMessage() + exception.getMessage()));
        }
        return Optional.of(userReturned);
    }
}
