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
    public Optional<StudentProfile> findById(BigInteger studentProfileId) {
        Optional<StudentProfile> studentProfileById = Optional.empty();
        try {
            studentProfileById = studentProfileService.findById(studentProfileId);
            studentProfileById.get().setMessageResponse(new MessageResponse(ResponseCode.STUDENT_PROFILE_EXISTS.getID(),
                    ResponseCode.STUDENT_PROFILE_EXISTS.getMessage()));
        } catch(Exception exception) {
            exception.printStackTrace();
            studentProfileById.get().setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),
                    ResponseCode.FAILURE.getMessage()));
        }
        return studentProfileById;
    }

//    @Override
//    public MessageResponse save(StudentProfile studentProfile) {
//        MessageResponse messageResponse = new MessageResponse();
//        try {
//            StudentProfile savedProfile = new StudentProfile();
//            if(!studentProfileService.findById(studentProfile.getStudentProfileId()).isPresent()){
//                savedProfile = studentProfileService.save(studentProfile);
//            } else {
//                savedProfile = studentProfileService.update(studentProfile);
//            }
//            messageResponse.setCode(savedProfile.getMessageResponse().getCode());
//            messageResponse.setMessage(savedProfile.getMessageResponse().getMessage());
//            return messageResponse;
//        } catch(Exception exception) {
//            exception.printStackTrace();
//            messageResponse.setCode(ResponseCode.STUDENT_PROFILE_SAVE_FAILURE.getID());
//            messageResponse.setMessage(ResponseCode.STUDENT_PROFILE_SAVE_FAILURE.getMessage() + exception.getMessage());
//            return messageResponse;
//        }
//    }

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
//        find out userId from studentProfile
//        Try to delete
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
