package com.anodiam.CRUDStudentProfile.model.common;
public enum ResponseCode {
    SUCCESS(0, "SUCCESS: Microsvc003: Student Profile: Success!"),
    FAILURE(1, "ERR: Microsvc003: Student Profile: Failure with exception message: "),
    PROFILE_ALREADY_PRESENT(12, "ERR: Microsvc003: Student Profile: CREATE: Student Profile already exists, try updating!"),
    USER_ABSENT(22, "ERR: Microsvc003: Student Profile: READ: User invalid or absent!"),
    PROFILE_DOES_NOT_EXIST(23, "ERR: Microsvc003: Student Profile: READ: Student Profile does not exist!"),
    STUDENT_PROFILE_DELETE_SUCCESS(40, "SUCCESS: Microsvc003: DELETE: Student Profile: Delete successful!"),
    PROFILE_DELETE_FAILED(41, "ERR: Microsvc003: Student Profile: DELETE: Profile Delete Failure with exception message: !"),
    USER_INVALID_PROFILE_DELETE_FAILED(42, "ERR: Microsvc003: DELETE: Student Profile: Profile Delete failed, User invalid!"),
    INVALID_PROFILE_DELETE_FAILED(43, "ERR: Microsvc003: DELETE: Student Profile: Invalid Student Profile, Delete failed!");

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