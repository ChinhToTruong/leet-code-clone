package com.zev.application.common;

public class Constants {

    public interface AppStatus {
        String ACTIVE = "ACTIVE";
        String INACTIVE = "INACTIVE";
        String DELETED = "DELETED";
    }

    public interface AppFlag {
        String enable = "1";
        String disable = "0";
    }

    public interface TokenType {
        String ACCESS_TOKEN = "ACCESS_TOKEN";
        String REFRESH_TOKEN = "REFRESH_TOKEN";
    }

    public interface Message {
        String TOKEN_EXPIRED = "token_expired";
        String TOKEN_INVALID = "token_invalid";
        String TEACHER_NOT_FOUND = "teacher_not_found";
    }

    public interface DateTimeFormat {
        String DATE_FORMAT = "yyyy-MM-dd";
        String TIME_FORMAT = "HH:mm:ss";
        String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }
}
