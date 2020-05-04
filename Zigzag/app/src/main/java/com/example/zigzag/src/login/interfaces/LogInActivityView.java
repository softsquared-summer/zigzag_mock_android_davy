package com.example.zigzag.src.login.interfaces;

public interface LogInActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void logInSuccess(boolean isSuccess, int code, String message ,String result);

}
