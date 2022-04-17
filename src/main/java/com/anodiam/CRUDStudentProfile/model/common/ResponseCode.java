package com.anodiam.CRUDStudentProfile.model.common;
public enum ResponseCode {
    SUCCESS(0, "SUCCESS: Microsvc003: Student Profile: Success!"),
    FAILURE(1, "ERR: Microsvc003: Student Profile: Failure with exception message: "),
    BLANK_ID(2, "ERR: Microsvc003: Student Profile: Student Profile ID is blank!"),
    USER_ALREADY_EXISTS(3, "ERR: Microsvc003: Student Profile: Username already exists!"),
    PROFILE_INVALID(4, "ERR: Microsvc003: Student Profile: Student Profile invalid!"),
    USER_ID_ABSENT(5, "ERR: Microsvc003: Student Profile: User Id absent!"),
    EMAIL_INVALID(6, "ERR: Microsvc003: Student Profile: Email invalid!");

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