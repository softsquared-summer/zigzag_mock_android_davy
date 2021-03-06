package com.example.zigzag.src.login;

import com.example.zigzag.src.login.interfaces.LogInActivityView;
import com.example.zigzag.src.login.interfaces.LogInRetrofitInterface;
import com.example.zigzag.src.login.models.LogInBody;
import com.example.zigzag.src.login.models.LogInResponse;
import com.example.zigzag.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class LoginService {
    private final LogInActivityView mLogInActivityView;

    LoginService(final LogInActivityView loginActivityView) {
        this.mLogInActivityView = loginActivityView;
    }

    void getTest() {
        final LogInRetrofitInterface loginRetrofitInterface = getRetrofit().create(LogInRetrofitInterface.class);
        loginRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mLogInActivityView.validateFailure(null);
                    return;
                }

                mLogInActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mLogInActivityView.validateFailure(null);
            }
        });
    }

    void postLogIn(String email, String password) {
        final LogInRetrofitInterface loginRetrofitInterface = getRetrofit().create(LogInRetrofitInterface.class);
        loginRetrofitInterface.LogInTest(new LogInBody(email, password)).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                final LogInResponse logInResponse = response.body();
                if (logInResponse == null) {
                    mLogInActivityView.validateFailure(null);
                    return;
                }

                mLogInActivityView.logInSuccess(logInResponse.getIsSuccess(), logInResponse.getCode(), logInResponse.getMessage(), logInResponse.getResult());
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                mLogInActivityView.validateFailure(null);
            }
        });
    }
}
