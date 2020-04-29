package com.example.zigzag.src.login.interfaces;

import com.example.zigzag.src.login.models.LogInResponse;

public interface LogInActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void logInSuccess(LogInResponse.LogInResult logInResult);

}
