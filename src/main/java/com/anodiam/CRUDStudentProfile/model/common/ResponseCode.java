package com.anodiam.CRUDStudentProfile.model.common;
public enum ResponseCode {
    SUCCESS(0, "SUCCESS: Microsvc003: Student Profile: Success!"),
    FAILURE(1, "ERR: Microsvc003: Student Profile: Failure with exception message: "),
    USER_ABSENT(12, "ERR: Microsvc003: Student Profile: FAILURE user absent!"),

    STUDENT_PROFILE_CREATE_SUCCESS(10, "SUCCESS: Microsvc003: Student Profile: CREATE: SUCCESS!"),
    STUDENT_PROFILE_CREATE_FAILURE(11, "ERR: Microsvc003: Student Profile: CREATE: FAILURE!"),
    STUDENT_PROFILE_CREATE_FAILURE_PROFILE_EXISTS(13, "ERR: Microsvc003: Student Profile: CREATE: FAILURE profile already exits, try updating!"),

    STUDENT_PROFILE_READ_SUCCESS(20, "SUCCESS: Microsvc003: Student Profile: READ: SUCCESS!"),
    STUDENT_PROFILE_READ_FAILURE(21, "ERR: Microsvc003: Student Profile: READ: FAILURE!"),
    STUDENT_PROFILE_READ_FAILURE_PROFILE_ABSENT(23, "ERR: Microsvc003: Student Profile: READ: FAILURE profile does not exist!"),

    STUDENT_PROFILE_UPDATE_SUCCESS(30, "SUCCESS: Microsvc003: Student Profile: UPDATE: SUCCESS!"),
    STUDENT_PROFILE_UPDATE_FAILURE(31, "ERR: Microsvc003: Student Profile: UPDATE: FAILURE!"),
    STUDENT_PROFILE_UPDATE_FAILURE_PROFILE_ABSENT(33, "ERR: Microsvc003: Student Profile: UPDATE: FAILURE profile does not exist, try creating!"),

    STUDENT_PROFILE_DELETE_SUCCESS(40, "SUCCESS: Microsvc003: Student Profile: DELETE: SUCCESS!"),
    STUDENT_PROFILE_DELETE_FAILURE(41, "ERR: Microsvc003: Student Profile: DELETE: FAILURE!"),
    STUDENT_PROFILE_DELETE_FAILURE_PROFILE_ABSENT(43, "ERR: Microsvc003: Student Profile: DELETE: FAILURE profile does not exist!");

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