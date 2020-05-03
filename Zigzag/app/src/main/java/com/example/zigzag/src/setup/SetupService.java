package com.example.zigzag.src.setup;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.setup.interfaces.SetupActivityView;
import com.example.zigzag.src.setup.interfaces.SetupRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class SetupService {
    private final SetupActivityView mSetupActivityView;

    SetupService(final SetupActivityView setupActivityView) {
        this.mSetupActivityView = setupActivityView;
    }

    void getTest() {
        final SetupRetrofitInterface setupRetrofitInterface = getRetrofit().create(SetupRetrofitInterface.class);
        setupRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mSetupActivityView.validateFailure(null);
                    return;
                }

                mSetupActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mSetupActivityView.validateFailure(null);
            }
        });
    }

}
