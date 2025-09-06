package com.zev.application.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SERVER_INTERNAL_ERROR("500", "Server Internal Error"),
    INPUT_INVALID("203", "Input Invalid"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    USER_ALREADY_EXIST("1", "User Already Exist"),
    USER_NOT_EXIST("2", "User Not Exist"),

    ROLE_ALREADY_EXIST("3", "Role Already Exist"),
    ROLE_NOT_EXIST("4", "Role Not Exist"),
    PERMISSION_NOT_FOUND("5", "Permission Not Found" ),
    CLASS_ROOM_NOT_FOUND("6", "Class Room Not Found" ),
    CLASS_ROOM_NAME_ALREADY_EXIST_WITH_THIS_USER("7", "Class Room Name Already Exist with this User" ),
    CLASS_ROOM_NOT_OWNER_BY_CURRENT_USER("8", "Class Room Not Owner By Current User" ),
    EXERCISE_NOT_FOUND("9", "Exercise Not Found" ),

    ;

    private final String code;
    private final String message;
}
