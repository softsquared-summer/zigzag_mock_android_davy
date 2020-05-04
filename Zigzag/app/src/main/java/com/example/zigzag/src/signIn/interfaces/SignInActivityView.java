package com.example.zigzag.src.signIn.interfaces;

public interface SignInActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void SignInSuccess(boolean isSuccess, int code, String message);

}
