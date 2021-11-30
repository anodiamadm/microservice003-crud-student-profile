package com.anodiam.CRUDStudentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.serviceRepository.Message.MessageService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile.StudentProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
class JwtAuthApplicationTests {

	@Autowired
	private StudentProfileService studentProfileService;

	@Autowired
	private MessageService messageService;

	int language_Id=CRUDStudentProfileApplication.languageId;

	@Test
	void contextLoads() {
	}

	//	Use Case 1.1: If username < 8 chars, I should NOT be able to register. I should get the message
	//	"Student registration failure! Username should be 8 or more characters long.".
	@Test
	public void testNegativeNewStudentProfileIDNotZero() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudent_profile_id(BigInteger.valueOf(-1));
		testStudentProfile.setFullName("Vicky");
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_ID_BLANK");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}

}
