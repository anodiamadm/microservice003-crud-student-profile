package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user;

import com.anodiam.CRUDStudentProfile.model.User;

import java.util.Optional;

abstract class UserServiceImpl implements UserService {

    @Override
    public Optional<User> findByUsername(String username) {
        return new UserServiceDal().findByUsername(username);
    }
}
