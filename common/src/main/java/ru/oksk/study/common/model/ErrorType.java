package ru.oksk.study.common.model;

public enum ErrorType {
    BANNED_BY_ORIGINATOR(801),
    BANNED_BY_OPERATOR(802),
    EXTERNAL_SERVER_ERROR(803),
    INCORRECT_MOBILE_NUMBER(804),
    SERVER_NOT_AVAILABLE(805),
    EXCEPTION(806);

    private int code;
    ErrorType(int errorCode) {
        this.code = errorCode;
    }

    public int getCode() {
        return code;
    }
}
