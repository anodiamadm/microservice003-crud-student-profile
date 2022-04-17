package com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board.BoardRepository;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
class StudentProfileServiceDal extends StudentProfileServiceImpl {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private LevelRepository levelRepository;

    public StudentProfileServiceDal(){}

    @Override
    public Optional<StudentProfile> findByUser(User user)
    {
        Optional<StudentProfile> optionalStudentProfile;
        try
        {
            optionalStudentProfile = studentProfileRepository.findByUser(user);
            if(optionalStudentProfile.isPresent())
            {
                GeneralEncoderDecoder generalEncoderDecoder=new GeneralEncoderDecoder();
                StudentProfile studentProfile= optionalStudentProfile.get();
                studentProfile=DencryptValues(studentProfile);
                studentProfile.setStudentProfileId(null);
                return  Optional.of(studentProfile);
            }
        }catch(Exception exception)
        {
            exception.printStackTrace();
            StudentProfile errorStudentProfile = new StudentProfile();
            errorStudentProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(), "Student Profile could not be retrieved!"));
            optionalStudentProfile = Optional.of(errorStudentProfile);
        }
        return optionalStudentProfile;
    }


    @Override
    public StudentProfile save(StudentProfile studentProfile)
    {
        Optional<StudentProfile> optionalStudentProfile = studentProfileRepository.findByUser(studentProfile.getUser());
        if(!optionalStudentProfile.isPresent())
        {
            return addStudentProfile(studentProfile);
        }
        else
        {
            StudentProfile existingStudentProfile= optionalStudentProfile.get();
            studentProfile.setStudentProfileId(existingStudentProfile.getStudentProfileId());
            return modifyStudentProfile(studentProfile);
        }
    }


    private StudentProfile addStudentProfile(StudentProfile studentProfile)
    {
        try
        {
            studentProfile.setStudentProfileId(BigInteger.valueOf(0));
            if(studentProfile.getBoardId()!=null)
            {
                studentProfile.setBoard(boardRepository.findById(studentProfile.getBoardId()).get());
            }
            if(studentProfile.getLevelId()!=null)
            {
                studentProfile.setLevel(levelRepository.findById(studentProfile.getLevelId()).get());
            }
            String returnMessage=ValidateBeforeSave(studentProfile,"save");
            if(returnMessage.length() > 0)
            {
                studentProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),returnMessage));
                return studentProfile;
            }
            studentProfile=EncryptValues(studentProfile);
            StudentProfile studentProfileToSave = studentProfileRepository.save(studentProfile);
            studentProfileToSave.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(),returnMessage));
            return studentProfileToSave;
        } catch (Exception exception) {
            studentProfile.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(),exception.getMessage()));
            return studentProfile;
        }
    }


    private StudentProfile modifyStudentProfile(StudentProfile studentProfile)
    {
        try
        {
            if(studentProfile.getBoardId()!=null)
            {
                studentProfile.setBoard(boardRepository.findById(studentProfile.getBoardId()).get());
            }
            if(studentProfile.getLevelId()!=null)
            {
                studentProfile.setLevel(levelRepository.findById(studentProfile.getLevelId()).get());
            }
            String returnMessage=ValidateBeforeSave(studentProfile,"modify");
            if(returnMessage.length() > 0)
            {
                studentProfile.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(),returnMessage));
                return studentProfile;
            }
            StudentProfile studentProfileToModify= studentProfileRepository.findById(studentProfile.getStudentProfileId()).get();
            if(studentProfileToModify!=null)
            {
                studentProfileToModify.setFullName(studentProfile.getFullName());
                studentProfileToModify.setPhoneNumber(studentProfile.getPhoneNumber());
                studentProfileToModify.setAddress(studentProfile.getAddress());
                studentProfileToModify.setLatitude(studentProfile.getLatitude());
                studentProfileToModify.setLongitude(studentProfile.getLongitude());
                studentProfileToModify.setGuardiansName(studentProfile.getGuardiansName());
                studentProfileToModify.setGuardiansEmail(studentProfile.getGuardiansEmail());
                studentProfileToModify.setGuardiansPhoneNumber(studentProfile.getGuardiansPhoneNumber());
                studentProfileToModify.setBoard(studentProfile.getBoard());
                studentProfileToModify.setLevel(studentProfile.getLevel());
                if(studentProfile.getProfileImageLink()!=null &&
                        studentProfile.getProfileImageLink().trim().length() > 0)
                {
                    studentProfileToModify.setProfileImageLink(studentProfile.getProfileImageLink());
                }
                studentProfileToModify.setUser(studentProfile.getUser());
                studentProfileToModify=EncryptValues(studentProfileToModify);
                StudentProfile student = studentProfileRepository.save(studentProfileToModify);
                studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(), returnMessage));
                return student;
            }
            studentProfileToModify.setMessageResponse(new MessageResponse(ResponseCode.FAILURE.getID(), returnMessage));
            return studentProfileToModify;
        } catch (Exception exception) {
            studentProfile.setMessageResponse(new MessageResponse(ResponseCode.SUCCESS.getID(),exception.getMessage()));
            return studentProfile;
        }
    }

    @Override
    public MessageResponse removeOne(BigInteger studentProfileId)
    {
        MessageResponse messageResponse = new MessageResponse();
        try
        {
            studentProfileRepository.deleteById(studentProfileId);
            messageResponse = new MessageResponse(ResponseCode.SUCCESS.getID(), ResponseCode.SUCCESS.getMessage());
        }catch(Exception exception)
        {
            exception.printStackTrace();
            messageResponse = new MessageResponse(ResponseCode.FAILURE.getID(), ResponseCode.FAILURE.getMessage());
        }
        return messageResponse;
    }

    private String ValidateBeforeSave(StudentProfile studentProfile,String methodName)
    {
        if(methodName=="save")
        {
            if(studentProfile.getStudentProfileId().intValue()!=0)
            {
                return ResponseCode.BLANK_ID.getMessage();
            }
            Optional<StudentProfile> optStudentProfile= studentProfileRepository.findByUser(studentProfile.getUser());
            if(optStudentProfile.isPresent())
            {
                return ResponseCode.USER_ALREADY_EXISTS.getMessage();
            }

        }
        if(methodName=="modify")
        {
            if(studentProfile.getStudentProfileId().intValue()<=0)
            {
                return ResponseCode.PROFILE_INVALID.getMessage();
            }
            Optional<StudentProfile> optStudentProfile= studentProfileRepository.findByUser(studentProfile.getUser());
            if(!optStudentProfile.isPresent())
            {
                return ResponseCode.USER_ID_ABSENT.getMessage();
            }
        }

        if(studentProfile.getGuardiansEmail()!=null &&
                studentProfile.getGuardiansEmail().trim().length() > 0 &&
                !isValidEmail(studentProfile.getGuardiansEmail()))
        {
            return ResponseCode.EMAIL_INVALID.getMessage();
        }

        return "";
    }

    private static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private StudentProfile EncryptValues(StudentProfile studentProfile)
    {
        try
        {
            GeneralEncoderDecoder encoderDecoder = new GeneralEncoderDecoder();
            studentProfile.setFullName(encoderDecoder.encrypt(studentProfile.getFullName()));
            studentProfile.setPhoneNumber(encoderDecoder.encrypt(studentProfile.getPhoneNumber()));
            studentProfile.setAddress(encoderDecoder.encrypt(studentProfile.getAddress()));
            studentProfile.setLatitude(encoderDecoder.encrypt(studentProfile.getLatitude()));
            studentProfile.setLongitude(encoderDecoder.encrypt(studentProfile.getLongitude()));
            studentProfile.setGuardiansName(encoderDecoder.encrypt(studentProfile.getGuardiansName()));
            studentProfile.setGuardiansEmail(encoderDecoder.encrypt(studentProfile.getGuardiansEmail()));
            studentProfile.setGuardiansPhoneNumber(encoderDecoder.encrypt(studentProfile.getGuardiansPhoneNumber()));
        }catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return studentProfile;
    }

    private StudentProfile DencryptValues(StudentProfile studentProfile)
    {
        try
        {
            GeneralEncoderDecoder encoderDecoder = new GeneralEncoderDecoder();
            studentProfile.setFullName(encoderDecoder.decrypt(studentProfile.getFullName()));
            studentProfile.setPhoneNumber(encoderDecoder.decrypt(studentProfile.getPhoneNumber()));
            studentProfile.setAddress(encoderDecoder.decrypt(studentProfile.getAddress()));
            studentProfile.setLatitude(encoderDecoder.decrypt(studentProfile.getLatitude()));
            studentProfile.setLongitude(encoderDecoder.decrypt(studentProfile.getLongitude()));
            studentProfile.setGuardiansName(encoderDecoder.decrypt(studentProfile.getGuardiansName()));
            studentProfile.setGuardiansEmail(encoderDecoder.decrypt(studentProfile.getGuardiansEmail()));
            studentProfile.setGuardiansPhoneNumber(encoderDecoder.decrypt(studentProfile.getGuardiansPhoneNumber()));
        }catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return studentProfile;
    }
}
