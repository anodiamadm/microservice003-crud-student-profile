package com.anodiam.CRUDStudentProfile;

import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile.StudentProfileService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
class JwtAuthApplicationTests {

	@Autowired
	private StudentProfileService studentProfileService;

	@Autowired
	private UserService userService;
//	STUDENT_PROFILE_READ_FAILURE_USER_ABSENT(22, "ERR: Microsvc003: Student Profile: READ: FAILURE user absent!"),
//	STUDENT_PROFILE_READ_FAILURE_PROFILE_ABSENT(23, "ERR: Microsvc003: Student Profile: READ: FAILURE profile does not exist!"),
//	STUDENT_PROFILE_READ_SUCCESS(20, "SUCCESS: Microsvc003: Student Profile: READ: SUCCESS!"),

//	STUDENT_PROFILE_UPDATE_FAILURE_USER_ABSENT(32, "ERR: Microsvc003: Student Profile: UPDATE: FAILURE user absent!"),
//	STUDENT_PROFILE_UPDATE_FAILURE_PROFILE_ABSENT(33, "ERR: Microsvc003: Student Profile: UPDATE: FAILURE profile does not exist, try creating!"),
//	STUDENT_PROFILE_UPDATE_SUCCESS(30, "SUCCESS: Microsvc003: Student Profile: UPDATE: SUCCESS!"),

//	STUDENT_PROFILE_DELETE_FAILURE_USER_ABSENT(42, "ERR: Microsvc003: Student Profile: DELETE: FAILURE user absent!"),
//	STUDENT_PROFILE_DELETE_FAILURE_PROFILE_ABSENT(43, "ERR: Microsvc003: Student Profile: DELETE: FAILURE profile does not exist!");
//	STUDENT_PROFILE_DELETE_SUCCESS(40, "SUCCESS: Microsvc003: Student Profile: DELETE: SUCCESS!"),
	private String actualMessage = "", expectedMessage = "";
	private Optional<StudentProfile> optionalStudentProfile = Optional.empty();
	private Optional<User> optionalUser = Optional.empty();

	//	CREATE: FAILURE: user absent
	@Test
	public void testNegativeCreateStudentProfileUserAbsent() throws Exception
	{
		optionalUser = userService.findByUsername("pinakisen");
		if(!optionalUser.isPresent()) {

		} else {

		}
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
		assertEquals(ResponseCode.STUDENT_PROFILE_CREATE_FAILURE_USER_ABSENT.getMessage(),
				studentProfile.getMessageResponse().getMessage());
		assertEquals(expectedMessage, actualMessage);
	}

	//	CREATE: FAILURE: profile already exits
	@Test
	public void testNegativeCreateStudentProfileProfileExists() throws Exception
	{
		User user = userService.findByUsername("pinaki.sen@gmail.com").get();
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
		assertEquals(ResponseCode.STUDENT_PROFILE_CREATE_FAILURE_PROFILE_EXISTS.getMessage(),
				studentProfile.getMessageResponse().getMessage());
	}

	//	CREATE: SUCCESS:
	@Test
	public void testNegativeReadStudentProfileAbsentUser() throws Exception
	{
		User user = userService.findByUsername("pinakisen").get();
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
		assertEquals(ResponseCode.USER_ABSENT.getMessage(),
				studentProfile.getMessageResponse().getMessage());
	}

	//	Read Student Profile Fail: If Student Profile does not Exist
	//	Response Code should say "PROFILE_DOES_NOT_EXIST"
	@Test
	public void testNegativeReadStudentProfileAbsentProfile() throws Exception
	{
		User user = userService.findByUsername("mala.das@gmail.com").get();
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
		assertEquals(ResponseCode.PROFILE_DOES_NOT_EXIST.getMessage(),
				studentProfile.getMessageResponse().getMessage());
	}

	//	Read Student Profile Success: If Student Profile exists
	//	Response Code should say "PROFILE_READ_SUCCESSFUL" and profile data should be available
	@Test
	public void testPositiveReadStudentProfile() throws Exception
	{
		User user = userService.findByUsername("pinaki.sen@gmail.com").get();
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
		assertEquals(ResponseCode.STUDENT_PROFILE_READ_SUCCESS.getMessage() + " for: "
						+ studentProfile.getFullName(),	studentProfile.getMessageResponse().getMessage());
	}

////	If Student Profile Exists then Student profile will be fetched
//	@Test
//	public void testPositiveStudentProfileExists() throws Exception {
//	}

	//	Use Case 1.1: If Student Profile Id not zero, then new student profile won't be added.
	/*@Test
	public void testNegativeNewStudentProfileIDNotZero() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(14));
		testStudentProfile.setFullName("Vicky");
		Language lng=new Language();
		lng.setLanguageId(language_Id);
		testStudentProfile.setLanguage(lng);
		StudentProfile newStudentProfile=studentProfileService.save(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_ID_BLANK");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}*/

	//	Use Case 1.2: If Student Profile Id not zero, then new student profile won't be added.
	/*@Test
	public void testNegativeExistingStudentProfileIDZeroOrLessThanZero() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(0));
		testStudentProfile.setFullName("Vicky");
		StudentProfile newStudentProfile=studentProfileService.modify(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_PROFILE_ID_INVALID");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}*/

	//	Use Case 1.4: If Language is null, then existing student profile won't be modified.
	/*@Test
	public void testNegativeLanguageNullInModifyMode() throws Exception
	{
		StudentProfile testStudentProfile=new StudentProfile();
		testStudentProfile.setStudentProfileId(BigInteger.valueOf(1));
		testStudentProfile.setFullName("Vicky");
		User existingUser=userService.GetSingleUser();
		testStudentProfile.setUser(existingUser);
		StudentProfile newStudentProfile=studentProfileService.modify(testStudentProfile);
		String returnMessage=messageService.showMessage(language_Id,"STUDENT_LANGUAGE_ID_BLANK");
		assertEquals(newStudentProfile.getMessageResponse().getMessage(),returnMessage);
	}*/

	//	Use Case 3.4.1: If email is invalid, =>
	//1. Does not contain exactly one '@' character.
	//2. Does not contain one or more '.' characters after the '@' character.
	//3. Does not contain any alphabet (a-z, A-Z) before '@'.
	//4. Does not contain any alphabet (a-z, A-Z) in between '@' and '.'.
	//5. Does not contain any alphabet (a-z, A-Z) after the last '.' character.
	// I should NOT be able to register. with the following message:
	//	"Student registration failure! Invalid email address."

}
