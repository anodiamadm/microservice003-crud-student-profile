package com.anodiam.CRUDStudentProfile;

import com.anodiam.CRUDStudentProfile.model.Permission;
import com.anodiam.CRUDStudentProfile.model.Role;
import com.anodiam.CRUDStudentProfile.model.StudentProfile;
import com.anodiam.CRUDStudentProfile.model.User;
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

import java.math.BigInteger;
import java.util.Optional;

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

	//	CREATE: FAILURE: user absent
	@Test
	public void testNegativeCreateStudentProfileUserAbsent() throws Exception
	{
	}

	//	CREATE: FAILURE: profile already exits
	@Test
	public void testNegativeCreateStudentProfileProfileExists() throws Exception
	{
		User user = userService.findByUsername("pinaki.sen@gmail.com").get();
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
		assertEquals(ResponseCode.STUDENT_PROFILE_CREATE_FAILURE.getMessage(),
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
	@Test
	public void testNegativeReadStudentProfileAbsentProfile() throws Exception
	{
		User user = userService.findByUsername("mala.das@gmail.com").get();
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
	}

	//	Response Code should say "PROFILE_READ_SUCCESSFUL" and profile data should be available
	@Test
	public void testPositiveReadStudentProfile() throws Exception
	{
		User user = userService.findByUsername("pinaki.sen@gmail.com").get();
		StudentProfile studentProfile = studentProfileService.findByUser(user).get();
		assertEquals(ResponseCode.STUDENT_PROFILE_READ_SUCCESS.getMessage() + " for: "
						+ studentProfile.getFullName(),	studentProfile.getMessageResponse().getMessage());
	}
}
