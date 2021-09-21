package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<StudentProfile> findById(BigInteger studentProfileId) {
        return studentProfileRepository.findById(studentProfileId);
    }

    @Override
    public StudentProfile save(StudentProfile studentProfile, User user) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);
            BigInteger savedStudentProfileId = savedStudentProfile.getStudentProfileId();
            user.setStudentProfile(savedStudentProfile);
            userRepository.save(user);
            messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(),
                    "Successfully Saved User Info and Student Profile!");
            savedStudentProfile.setMessageResponse(messageResponse);
            return savedStudentProfile;
        } catch (Exception exception) {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),
                    "Failed to Save Student Profile!");
            StudentProfile failedToSaveProfile = new StudentProfile();
            failedToSaveProfile.setMessageResponse(messageResponse);
            return failedToSaveProfile;
        }
    }

    @Override
    public StudentProfile modify(StudentProfile studentProfile, User user) {
        MessageResponse messageResponse = new MessageResponse();
        try {
            if(studentProfileRepository.findById(studentProfile.getStudentProfileId())!=null) {
                StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);
//            SAVING USER With StudentProfile ID
//            BigInteger savedStudentProfileId = savedStudentProfile.getStudentProfileId();
//            User user = userRepository;
//            user.setStudentProfile();
//            userRepository.save(user);
                messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(),
                        "Successfully Modified Student Profile!");
                savedStudentProfile.setMessageResponse(messageResponse);
                return savedStudentProfile;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(),
                "Failed to Modify Student Profile!");
        StudentProfile failedToSaveProfile = new StudentProfile();
        failedToSaveProfile.setMessageResponse(messageResponse);
        return failedToSaveProfile;
    }
}
