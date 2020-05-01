package com.example.zigzag.src.outer;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.outer.interfaces.OuterActivityView;
import com.example.zigzag.src.outer.interfaces.OuterRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class OuterService {
    private final OuterActivityView mOuterActivityView;

    OuterService(final OuterActivityView outerActivityView) {
        this.mOuterActivityView = outerActivityView;
    }

    void getTest() {
        final OuterRetrofitInterface outerRetrofitInterface = getRetrofit().create(OuterRetrofitInterface.class);
        outerRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mOuterActivityView.validateFailure(null);
                    return;
                }

                mOuterActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mOuterActivityView.validateFailure(null);
            }
        });
    }
}
