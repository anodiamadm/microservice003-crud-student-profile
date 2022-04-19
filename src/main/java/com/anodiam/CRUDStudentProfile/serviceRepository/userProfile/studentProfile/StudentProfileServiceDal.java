package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileService studentProfileService;

    @Autowired
    private UserService userService;

    public StudentProfileServiceDal(){}

    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        StudentProfile savedProfile = new StudentProfile();
        try {
            savedProfile = studentProfileService.save(studentProfile);
            savedProfile.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(),
                    ResponseCode.SUCCESS.getMessage()));
            return savedProfile;
        } catch (Exception exception) {
            exception.printStackTrace();
            savedProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),
                    ResponseCode.FAILURE.getMessage() + exception.getMessage()));
            return savedProfile;
        }
    }

    @Override
    public Optional<StudentProfile> findByUser(User user) {
        Optional<StudentProfile> studentProfileByUser = Optional.empty();
        try {
            Optional<User> optionalUser = userService.findByUsername(user.getUsername());
            if(!optionalUser.isPresent()) {
                studentProfileByUser.get().setMessageResponse(new MessageResponse(ResponseCode.USER_ABSENT.getID(),
                        ResponseCode.USER_ABSENT.getMessage()));
                return  studentProfileByUser;
            }
            studentProfileByUser = studentProfileService.findByUser(user);
            if(studentProfileByUser.isPresent())
            {
                studentProfileByUser.get().setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(),
                        ResponseCode.SUCCESS.getMessage()));
                return  studentProfileByUser;
            } else {
                studentProfileByUser.get().setMessageResponse(new MessageResponse(ResponseCode.PROFILE_DOES_NOT_EXIST.getID(),
                        ResponseCode.PROFILE_DOES_NOT_EXIST.getMessage()));
            }
        } catch(Exception exception) {
            exception.printStackTrace();
            studentProfileByUser.get().setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),
                    ResponseCode.FAILURE.getMessage()));
        }
        return studentProfileByUser;
    }

    @Override
    public MessageResponse removeOne(BigInteger studentProfileId) {
        MessageResponse messageResponse = new MessageResponse();
        studentProfileService.removeOne(studentProfileId);
        try{
            if(studentProfileId==null) {
                messageResponse.setCode(ResponseCode.INVALID_PROFILE_DELETE_FAILED.getID());
                messageResponse.setMessage(ResponseCode.INVALID_PROFILE_DELETE_FAILED.getMessage());
            } else {
                messageResponse = studentProfileService.removeOne(studentProfileId);
                messageResponse.setCode(ResponseCode.STUDENT_PROFILE_DELETE_SUCCESS.getID());
                messageResponse.setMessage(ResponseCode.STUDENT_PROFILE_DELETE_SUCCESS.getMessage());
            } return messageResponse;
        } catch(Exception exception) {
            exception.printStackTrace();
            messageResponse.setCode(ResponseCode.PROFILE_DELETE_FAILED.getID());
            messageResponse.setMessage(ResponseCode.PROFILE_DELETE_FAILED.getMessage() + exception.getMessage());
            return messageResponse;
        }
    }
}
