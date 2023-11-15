package ru.oksk.study.common.model;

public class Error {

    private int code;
    private String message;
    private String exceptionCause;

    public Error() {
    }

    public Error(ErrorType errorType, Exception exception) {
        this.code = errorType.getCode();
        this.message = errorType.name();
        this.exceptionCause = exception.getMessage();
    }

    public Error(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.name();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionCause() {
        return exceptionCause;
    }

    public void setExceptionCause(String exceptionCause) {
        this.exceptionCause = exceptionCause;
    }
}
