package com.anodiam.CRUDStudentProfile.security;

import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserRepository;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Handle User Not Found exception here *****
        User user = userService.findByUsername(s).get();
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
