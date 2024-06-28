package com.taskmanager.constants.enums.exceptionCodes;

/**
 * @author harjeevanSingh
 */

public enum RootExceptionCodes implements ExceptionCodes {
    TASK_NOT_FOUND("BE_501", "Task not found!"),
    DUPLICATE_TASK_TITLE("BE_502", "Duplicate task title!"),
    ;

    private final String code;
    private final String message;

    RootExceptionCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
