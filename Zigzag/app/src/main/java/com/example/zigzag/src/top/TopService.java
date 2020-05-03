package com.example.zigzag.src.top;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.top.interfaces.TopActivityView;
import com.example.zigzag.src.top.interfaces.TopRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class TopService {
    private final TopActivityView mTopActivityView;

    TopService(final TopActivityView topActivityView) {
        this.mTopActivityView = topActivityView;
    }

    void getTest() {
        final TopRetrofitInterface topRetrofitInterface = getRetrofit().create(TopRetrofitInterface.class);
        topRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mTopActivityView.validateFailure(null);
                    return;
                }

                mTopActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mTopActivityView.validateFailure(null);
            }
        });
    }
}
