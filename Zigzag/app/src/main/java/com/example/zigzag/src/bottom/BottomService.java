package com.example.zigzag.src.bottom;

import com.example.zigzag.src.bottom.interfaces.BottomActivityView;
import com.example.zigzag.src.bottom.interfaces.BottomRetrofitInterface;
import com.example.zigzag.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class BottomService {
    private final BottomActivityView mBottomActivityView;

    BottomService(final BottomActivityView bottomActivityView) {
        this.mBottomActivityView = bottomActivityView;
    }

    void getTest() {
        final BottomRetrofitInterface bottomRetrofitInterface = getRetrofit().create(BottomRetrofitInterface.class);
        bottomRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mBottomActivityView.validateFailure(null);
                    return;
                }

                mBottomActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mBottomActivityView.validateFailure(null);
            }
        });
    }
}
