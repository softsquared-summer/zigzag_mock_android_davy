package com.example.zigzag.src.dress;

import com.example.zigzag.src.dress.interfaces.DressActivityView;
import com.example.zigzag.src.dress.interfaces.DressRetrofitInterface;
import com.example.zigzag.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class DressService {
    private final DressActivityView mDressActivityView;

    DressService(final DressActivityView dressActivityView) {
        this.mDressActivityView = dressActivityView;
    }

    void getTest() {
        final DressRetrofitInterface dressRetrofitInterface = getRetrofit().create(DressRetrofitInterface.class);
        dressRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mDressActivityView.validateFailure(null);
                    return;
                }

                mDressActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mDressActivityView.validateFailure(null);
            }
        });
    }
}
