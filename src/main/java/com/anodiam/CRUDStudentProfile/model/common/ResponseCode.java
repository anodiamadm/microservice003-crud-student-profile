package com.anodiam.CRUDStudentProfile.model.common;
public enum ResponseCode {
    SUCCESS(0, "SUCCESS: Microsvc003: Student Profile: Success!"),
    FAILURE(1, "ERR: Microsvc003: Student Profile: Failure with exception message: "),
    INVALID_CURRENT_USER(2, "ERR: Microsvc003: Student Profile: Invalid current user! "),
    USER_EXISTS(3, "Microsvc003: Student Profile: Username / Email exists: "),
    BOARD_ID_NOT_FOUND(5, "ERR: Microsvc003: Student Profile: Board Id not found! "),
    LEVEL_ID_NOT_FOUND(6, "ERR: Microsvc003: Student Profile: Level Id not found! "),
    STUDENT_PROFILE_CREATE_SUCCESS(10, "SUCCESS: Microsvc003: Student Profile: CREATE: SUCCESS!"),
    USER_ABSENT(11, "ERR: Microsvc003: Student Profile: FAILURE user absent! "),
    USER_EXISTS_PROFILE_ABSENT(12, "ERR: Microsvc003: Student Profile: FAILURE profile absent: "),
    STUDENT_PROFILE_CREATE_FAILURE(13, "ERR: Microsvc003: Student Profile: CREATE: FAILURE profile already exits, try updating!"),
    STUDENT_PROFILE_SAVE_FAIL_FULL_NAME_SHORT(14, "ERR: Microsvc003: Student Profile: SAVE: FAILURE" +
            " Full Name too short: "),
    STUDENT_PROFILE_SAVE_FAIL_BOARD_INVALID(15, "ERR: Microsvc003: Student Profile: SAVE: FAILURE" +
            " Board Id Invalid: "),
    STUDENT_PROFILE_SAVE_FAIL_LEVEL_INVALID(16, "ERR: Microsvc003: Student Profile: SAVE: FAILURE" +
            " Level Id Invalid: "),
    STUDENT_PROFILE_READ_SUCCESS(20, "SUCCESS: Microsvc003: Student Profile: READ: SUCCESS!"),
    STUDENT_PROFILE_READ_FAILURE(21, "ERR: Microsvc003: Student Profile: READ: FAILURE !"),
    STUDENT_PROFILE_UPDATE_SUCCESS(30, "SUCCESS: Microsvc003: Student Profile: UPDATE: SUCCESS!"),
    STUDENT_PROFILE_SAVE_FAILURE(31, "ERR: Microsvc003: Student Profile: SAVE: FAILURE: "),
    STUDENT_PROFILE_DELETE_SUCCESS(40, "SUCCESS: Microsvc003: Student Profile: DELETE: SUCCESS!"),
    STUDENT_PROFILE_DELETE_FAILURE(41, "ERR: Microsvc003: Student Profile: DELETE: FAILURE profile does not exist!"),
    ROLE_NAME_EXISTS(100, "Microsvc003: Student Profile: Role name exists: "),
    ROLE_NAME_INVALID(101, "ERR: Microsvc003: Student Profile: Role name INVALID: "),
    PERMISSION_NAME_EXISTS(200, "Microsvc003: Student Profile: Permission name exists: "),
    PERMISSION_NAME_INVALID(201, "ERR: Microsvc003: Student Profile: Permission name INVALID: ");

    private Integer id;
    private String message;

    ResponseCode(Integer id, String message){
        this.id = id;
        this.message=message;
    }

    public Integer getID(){
        return id;
    }
    public String getMessage() {return message;}
}