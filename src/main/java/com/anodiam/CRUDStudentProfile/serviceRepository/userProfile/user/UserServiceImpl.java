package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user;

import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.address.Country;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Country.CountryService;
import com.anodiam.CRUDStudentProfile.serviceRepository.address.Country.CountryServiceDal;

import java.util.List;
import java.util.Optional;

abstract class UserServiceImpl implements UserService {

    @Override
    public Optional<User> findByUsername(String username) {
        return new UserServiceDal().findByUsername(username);
    }
}
