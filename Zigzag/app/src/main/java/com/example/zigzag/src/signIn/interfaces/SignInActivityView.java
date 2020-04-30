package com.example.zigzag.src.signIn.interfaces;

import com.example.zigzag.src.signIn.models.SignInResponse;

public interface SignInActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void SignInSuccess(SignInResponse.SignInResult signInResult);

}
