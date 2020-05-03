package com.example.zigzag.src.top.tshirt;

import com.example.zigzag.src.home.interfaces.HomeActivityView;
import com.example.zigzag.src.home.interfaces.HomeRetrofitInterface;
import com.example.zigzag.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class TShirtService {
    private final HomeActivityView mHomeActivityView;

    TShirtService(final HomeActivityView homeActivityView) {
        this.mHomeActivityView = homeActivityView;
    }

    void getTest() {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);
        homeRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mHomeActivityView.validateFailure(null);
                    return;
                }

                mHomeActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mHomeActivityView.validateFailure(null);
            }
        });
    }
}
