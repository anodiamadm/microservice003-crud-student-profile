package com.anodiam.CRUDStudentProfile;

import com.anodiam.CRUDStudentProfile.model.Permission;
import com.anodiam.CRUDStudentProfile.model.Role;
import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;
import com.anodiam.CRUDStudentProfile.model.common.ResponseCode;
import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board.BoardService;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level.LevelService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Permission.PermissionService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.Role.RoleService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.studentProfile.StudentProfileService;
import com.anodiam.CRUDStudentProfile.serviceRepository.userProfile.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;


@SpringBootTest
class JwtAuthApplicationTests {

	@Autowired
	private StudentProfileService studentProfileService;

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private LevelService levelService;

	//	Role should not be fetched against invalid Role Name
	@Test
	public void testNegativeFindRoleByInvalidRoleName() throws Exception
	{
		String roleName="INVALID_ROLE";
		Role role = roleService.findByRoleName(roleName).get();
		assertEquals(ResponseCode.ROLE_NAME_INVALID.getMessage() + roleName,
				role.getMessageResponse().getMessage());
	}

	//	Correct Role should be fetched against valid Role Name
	@Test
	public void testPositiveFindRoleByValidRoleName() throws Exception
	{
		String roleName="USER";
		Role role = roleService.findByRoleName(roleName).get();
		assertEquals(ResponseCode.ROLE_NAME_EXISTS.getMessage() + role.getRoleName(),
				role.getMessageResponse().getMessage());
	}

	//	Permission should not be fetched against invalid Permission Name
	@Test
	public void testNegativeFindPermissionByInvalidPermissionName() throws Exception
	{
		String permissionName="INVALID_ACCESS";
		Permission permission = permissionService.findByPermissionName(permissionName).get();
		assertEquals(ResponseCode.PERMISSION_NAME_INVALID.getMessage() + permissionName,
				permission.getMessageResponse().getMessage());
	}

	//	Correct Permission should be fetched against valid Permission Name
	@Test
	public void testPositiveFindPermissionByValidPermissionName() throws Exception
	{
		String permissionName="STUDENT_ACCESS";
		Permission permission = permissionService.findByPermissionName(permissionName).get();
		assertEquals(ResponseCode.PERMISSION_NAME_EXISTS.getMessage()
				+ permission.getPermissionName(), permission.getMessageResponse().getMessage());
	}

	//	User should NOT be fetched against invalid Email
	@Test
	public void testNegativeFindUserByWrongEmail() throws Exception
	{
		String email="invalidemail@gmail.com";
		User foundUser = userService.findByUsername(email).get();
		assertEquals(ResponseCode.USER_ABSENT.getMessage() + email,
				foundUser.getMessageResponse().getMessage());
	}

	//	Correct User should be fetched against valid Email
	@Test
	public void testPositiveFindUserByCorrectEmail() throws Exception
	{
		String email="pinaki.sen@gmail.com";
		User foundUser = userService.findByUsername(email).get();
		assertEquals(ResponseCode.USER_EXISTS.getMessage() + foundUser.getUsername(),
				foundUser.getMessageResponse().getMessage());
	}

	//	Get Board for invalid BoardId = 2000
	@Test
	public void testNegativeGetBoardByInvalidBoardId() throws Exception {
		String response = "";
		try {
			Optional<Board> optBoard = boardService.findById(BigInteger.valueOf(2000));
			if (optBoard.isPresent()) {
				response = optBoard.get().getMessageResponse().getMessage();
			}
		} catch (Exception exception) {
			response = exception.getMessage();
		}
		assertEquals(ResponseCode.BOARD_ID_NOT_FOUND.getMessage()
				+ (BigInteger.valueOf(2000)).toString(), response);
	}

	//	Get Board for valid BoardId = 2
	@Test
	public void testPositiveGetBoardByValidBoardId() throws Exception
	{
		String response = "";
		try {
			Optional<Board> optBoard = boardService.findById(BigInteger.valueOf(2));
			if(optBoard.isPresent()) {
				response = optBoard.get().getMessageResponse().getMessage();
			}
		} catch (Exception exception) {
			response = exception.getMessage();
		}
		assertEquals(ResponseCode.SUCCESS.getMessage()
				+ boardService.findById(BigInteger.valueOf(2)).get().getBoardName(), response);
	}

	//	Get Level for invalid levelId = 2000
	@Test
	public void testNegativeGetLevelByInvalidLevelId() throws Exception
	{
		String response = "";
		try {
			Optional<Level> optLevel = levelService.findById(BigInteger.valueOf(2000));
			if(optLevel.isPresent()) {
				response = optLevel.get().getMessageResponse().getMessage();
			}
		} catch (Exception exception) {
			response = exception.getMessage();
		}
		assertEquals(ResponseCode.LEVEL_ID_NOT_FOUND.getMessage()
				+ (BigInteger.valueOf(2000)).toString(), response);
	}

	//	Get Level for valid LevelId = 2
	@Test
	public void testPositiveGetLevelByValidLevelId() throws Exception
	{
		String response = "";
		try {
			Optional<Level> optLevel = levelService.findById(BigInteger.valueOf(2));
			if(optLevel.isPresent()) {
				response = optLevel.get().getMessageResponse().getMessage();
			}
		} catch (Exception exception) {
			response = exception.getMessage();
		}
		assertEquals(ResponseCode.SUCCESS.getMessage()
				+ levelService.findById(BigInteger.valueOf(2)).get().getLevelName(), response);
	}


	//	CREATE student profile: FAILURE: invalid full name
	@Test
	@Transactional
	public void testNegativeCreateStudentProfileShortFullName() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		String email = "mala.das@gmail.com";
		User user = userService.findByUsername(email).get();
		studentProfileService.deleteByUser(user);
		studentProfile.setUser(userService.findByUsername(email).get());
		studentProfile.setFullName("md");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		assertEquals(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_FULL_NAME_SHORT.getMessage()
						+ studentProfile.getFullName(), messageResponse.getMessage());
	}

	//	CREATE student profile: FAILURE: invalid levelId
	@Test
	@Transactional
	public void testNegativeCreateStudentProfileInvalidLevelId() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		String email = "mala.das@gmail.com";
		studentProfileService.deleteByUser(userService.findByUsername(email).get());
		studentProfile.setUser(userService.findByUsername(email).get());
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7000)).get());
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		assertEquals(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_LEVEL_INVALID.getMessage(),
				messageResponse.getMessage());
	}

	//	CREATE student profile: FAILURE: invalid boardId
	@Test
	@Transactional
	public void testNegativeCreateStudentProfileInvalidBoardId() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		String email = "mala.das@gmail.com";
		studentProfileService.deleteByUser(userService.findByUsername(email).get());
		studentProfile.setUser(userService.findByUsername(email).get());
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3000)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		assertEquals(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_BOARD_INVALID.getMessage(),
				messageResponse.getMessage());
	}

	//	CREATE student profile: SUCCESS
	@Test
	@Transactional
	public void testPositiveCreateStudentProfile() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		String email = "mala.das@gmail.com";
		User user = userService.findByUsername(email).get();
		studentProfileService.deleteByUser(user);
		studentProfile.setUser(userService.findByUsername(email).get());
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		assertEquals(ResponseCode.STUDENT_PROFILE_CREATE_SUCCESS.getMessage(),
				messageResponse.getMessage());
	}

	//	READ student profile: FAILURE: Valid user but profile absent
	@Test
	@Transactional
	public void testNegativeReadStudentProfileValidUserButProfileAbsent() throws Exception
	{
		User user = userService.findByUsername("mala.das@gmail.com").get();
		studentProfileService.deleteByUser(user);
		MessageResponse messageResponse = studentProfileService.deleteByUser(user);
		assertEquals(ResponseCode.USER_EXISTS_PROFILE_ABSENT.getMessage()
				+ user.getUsername(), messageResponse.getMessage());
	}

	//	READ student profile: SUCCESS: valid user profile present
	@Test
	public void testPositiveReadStudentProfileValidUserAndProfilePresent() throws Exception
	{
		StudentProfile studentProfile = new StudentProfile();
		String email = "mala.das@gmail.com";
		studentProfile.setUser(userService.findByUsername(email).get());
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		studentProfileService.save(studentProfile);
		MessageResponse messageResponse =
				studentProfileService.findByUser(studentProfile.getUser()).get().getMessageResponse();
		assertEquals(ResponseCode.STUDENT_PROFILE_READ_SUCCESS.getMessage()
				+ studentProfile.getUser().getUsername(), messageResponse.getMessage());
	}

	//	UPDATE student profile: FAILURE: Short username < 3 characters
	@Test
	@Transactional
	public void testNegativeUpdateStudentProfileInvalidUsername() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setFullName("MD");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		User user = userService.findByUsername("mala.das@gmail.com").get();
		studentProfile.setUser(user);
		studentProfileService.save(studentProfile);
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		studentProfileService.deleteByUser(user);
		assertEquals(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_FULL_NAME_SHORT.getMessage()
				+ studentProfile.getFullName(),	messageResponse.getMessage());
	}

	//	UPDATE student profile: FAILURE: invalid levelId
	@Test
	@Transactional
	public void testNegativeUpdateStudentProfileInvalidLevelId() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7000)).get());
		User user = userService.findByUsername("mala.das@gmail.com").get();
		studentProfile.setUser(user);
		studentProfileService.save(studentProfile);
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		studentProfileService.deleteByUser(user);
		assertEquals(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_LEVEL_INVALID.getMessage(),
				messageResponse.getMessage());
	}

	//	UPDATE student profile: FAILURE: invalid boardId
	@Test
	@Transactional
	public void testNegativeUpdateStudentProfileInvalidBoardId() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3000)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		User user = userService.findByUsername("mala.das@gmail.com").get();
		studentProfile.setUser(user);
		studentProfileService.save(studentProfile);
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		studentProfileService.deleteByUser(user);
		assertEquals(ResponseCode.STUDENT_PROFILE_SAVE_FAIL_BOARD_INVALID.getMessage(),
				messageResponse.getMessage());
	}

	//	UPDATE student profile: SUCCESS
	@Test
	@Transactional
	public void testPositiveUpdateStudentProfile() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		User user = userService.findByUsername("mala.das@gmail.com").get();
		studentProfile.setUser(user);
		studentProfileService.save(studentProfile);
		MessageResponse messageResponse = studentProfileService.save(studentProfile);
		studentProfileService.deleteByUser(user);
		assertEquals(ResponseCode.STUDENT_PROFILE_UPDATE_SUCCESS.getMessage(),
				messageResponse.getMessage());
	}

	//	Delete student profile: FAILURE: Valid user but profile absent
	@Test
	@Transactional
	public void testNegativeDeleteStudentProfileValidUserButProfileAbsent() throws Exception
	{
		String email = "mala.das@gmail.com";
		User user = userService.findByUsername(email).get();
		studentProfileService.deleteByUser(user);
		MessageResponse messageResponse = studentProfileService.deleteByUser(user);
		assertEquals(ResponseCode.USER_EXISTS_PROFILE_ABSENT.getMessage() + user.getUsername(),
				messageResponse.getMessage());
	}

	//	Delete student profile: SUCCESS: valid user profile present
	@Test
	@Transactional
	public void testPositiveDeleteStudentProfileValidUserAndProfilePresent() throws Exception
	{
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setFullName("Mala Das Test");
		studentProfile.setBoard(boardService.findById(BigInteger.valueOf(3)).get());
		studentProfile.setLevel(levelService.findById(BigInteger.valueOf(7)).get());
		User user = userService.findByUsername("mala.das@gmail.com").get();
		studentProfile.setUser(user);
		studentProfileService.save(studentProfile);
		MessageResponse messageResponse = studentProfileService.deleteByUser(user);
		assertEquals(ResponseCode.STUDENT_PROFILE_DELETE_SUCCESS.getMessage(),
				messageResponse.getMessage());
	}
}
