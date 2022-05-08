package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board.BoardService;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level.LevelService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private LevelService levelService;

    public StudentProfileServiceDal(){}

    @Override
    public Optional<StudentProfile> findByUser(User user) {
        StudentProfile studentProfileByUser = new StudentProfile();
        try {
            if(studentProfileRepository.findByUser(user).isPresent()) {
                studentProfileByUser = studentProfileRepository.findByUser(user).get();
                studentProfileByUser.setMessageResponse(new
                        MessageResponse(ResponseCode.STUDENT_PROFILE_READ_SUCCESS.getID(),
                        ResponseCode.STUDENT_PROFILE_READ_SUCCESS.getMessage() + user.getUsername()));
            } else {
                studentProfileByUser.setMessageResponse(new
                        MessageResponse(ResponseCode.USER_EXISTS_PROFILE_ABSENT.getID(),
                        ResponseCode.USER_EXISTS_PROFILE_ABSENT.getMessage() + user.getUsername()));
            }
        } catch(Exception exception) {
            exception.printStackTrace();
            studentProfileByUser.setMessageResponse(new
                    MessageResponse(ResponseCode.STUDENT_PROFILE_READ_FAILURE.getID(),
                    ResponseCode.STUDENT_PROFILE_READ_FAILURE.getMessage() + exception.getMessage()));
        }
        return Optional.of(studentProfileByUser);
    }

    @Override
    public MessageResponse deleteByUser(User user) {
        StudentProfile studentProfileDeleted = new StudentProfile();
        try{
            if (studentProfileRepository.findByUser(user).isPresent()) {
                if(studentProfileRepository.deleteByUser(user)==1) {
                    studentProfileDeleted.setMessageResponse(new
                            MessageResponse(ResponseCode.STUDENT_PROFILE_DELETE_SUCCESS.getID(),
                            ResponseCode.STUDENT_PROFILE_DELETE_SUCCESS.getMessage()));
                } else {
                    studentProfileDeleted.setMessageResponse(new
                            MessageResponse(ResponseCode.STUDENT_PROFILE_DELETE_FAILURE.getID(),
                            ResponseCode.STUDENT_PROFILE_DELETE_FAILURE.getMessage()));
                }
            } else {
                studentProfileDeleted.setMessageResponse(new
                        MessageResponse(ResponseCode.USER_EXISTS_PROFILE_ABSENT.getID(),
                        ResponseCode.USER_EXISTS_PROFILE_ABSENT.getMessage() + user.getUsername()));
            }
        } catch(Exception exception) {
            exception.printStackTrace();
            studentProfileDeleted.setMessageResponse(new
                    MessageResponse(ResponseCode.STUDENT_PROFILE_DELETE_FAILURE.getID(),
                    ResponseCode.STUDENT_PROFILE_DELETE_FAILURE.getMessage() + exception.getMessage()));
        }
        return studentProfileDeleted.getMessageResponse();
    }

    @Override
    public MessageResponse save(StudentProfile studentProfile) {
        StudentProfile studentProfileResponded = new StudentProfile();
        try{
            Optional<StudentProfile> existingStudentProfileByUser =
                    studentProfileRepository.findByUser(studentProfile.getUser());
            if(studentProfile.getFullName().length() < 3 || studentProfile.getFullName()==null) {
                return new MessageResponse(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_FULL_NAME_SHORT.getID(),
                        ResponseCode.STUDENT_PROFILE_SAVE_FAIL_FULL_NAME_SHORT.getMessage()
                                + studentProfile.getFullName());
            } else if (studentProfile.getLevel().getLevelId()==null ||
                    levelService.findById(studentProfile.getLevel().getLevelId())
                    .get().getMessageResponse().getCode() == ResponseCode.LEVEL_ID_NOT_FOUND.getID()) {
                return new MessageResponse(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_LEVEL_INVALID.getID(),
                        ResponseCode.STUDENT_PROFILE_SAVE_FAIL_LEVEL_INVALID.getMessage());
            } else if (studentProfile.getBoard().getBoardId()==null ||
                    boardService.findById(studentProfile.getBoard().getBoardId())
                    .get().getMessageResponse().getCode() == ResponseCode.BOARD_ID_NOT_FOUND.getID()) {
                return new MessageResponse(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_BOARD_INVALID.getID(),
                        ResponseCode.STUDENT_PROFILE_SAVE_FAIL_BOARD_INVALID.getMessage());
            } else if (existingStudentProfileByUser.isPresent()) {
                studentProfile.setStudentProfileId(existingStudentProfileByUser.get().getStudentProfileId());
                studentProfileResponded = studentProfileRepository.save(studentProfile);
                studentProfileResponded.setMessageResponse(new
                        MessageResponse(ResponseCode.STUDENT_PROFILE_UPDATE_SUCCESS.getID(),
                        ResponseCode.STUDENT_PROFILE_UPDATE_SUCCESS.getMessage()));
            } else {
                studentProfileResponded = studentProfileRepository.save(studentProfile);
                studentProfileResponded.setMessageResponse(new
                        MessageResponse(ResponseCode.STUDENT_PROFILE_CREATE_SUCCESS.getID(),
                        ResponseCode.STUDENT_PROFILE_CREATE_SUCCESS.getMessage()));
            }
        } catch(Exception exception) {
            exception.printStackTrace();
            studentProfileResponded.setMessageResponse(new
                    MessageResponse(ResponseCode.STUDENT_PROFILE_SAVE_FAILURE.getID(),
                    ResponseCode.STUDENT_PROFILE_SAVE_FAILURE.getMessage()));
        }
        return studentProfileResponded.getMessageResponse();
    }
}
