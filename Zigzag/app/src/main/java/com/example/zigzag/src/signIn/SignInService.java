package com.example.zigzag.src.signIn;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.signIn.interfaces.SignInActivityView;
import com.example.zigzag.src.signIn.interfaces.SignInRetrofitInterface;
import com.example.zigzag.src.signIn.models.SignInBody;
import com.example.zigzag.src.signIn.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class SignInService {
    private final SignInActivityView mSignInActivityView;

    SignInService(final SignInActivityView signinActivityView) {
        this.mSignInActivityView = signinActivityView;
    }

    void getTest() {
        final SignInRetrofitInterface signInRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);
        signInRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mSignInActivityView.validateFailure(null);
                    return;
                }

                mSignInActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mSignInActivityView.validateFailure(null);
            }
        });
    }

    void postSignIn(String email, String pw, String ph, String isOver14, String isServiceAgree, String isPrivacyAgree, String isAlarmAgree) {
        final com.example.zigzag.src.signIn.interfaces.SignInRetrofitInterface signInRetrofitInterface = getRetrofit().create(com.example.zigzag.src.signIn.interfaces.SignInRetrofitInterface.class);
        signInRetrofitInterface.SignInTest(new SignInBody(email, pw, ph, isOver14, isServiceAgree, isPrivacyAgree, isAlarmAgree)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SignInResponse signInResponse = response.body();
                if (signInResponse == null) {
                    mSignInActivityView.validateFailure(null);
                    return;
                }

                mSignInActivityView.SignInSuccess(signInResponse.getIsSuccess(), signInResponse.getCode(), signInResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mSignInActivityView.validateFailure(null);
            }
        });
    }
}
