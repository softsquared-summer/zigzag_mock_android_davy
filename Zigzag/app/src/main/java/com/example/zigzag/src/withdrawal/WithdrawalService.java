package com.example.zigzag.src.withdrawal;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.withdrawal.interfaces.WithdrawalActivityView;
import com.example.zigzag.src.withdrawal.interfaces.WithdrawalRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class WithdrawalService {
    private final WithdrawalActivityView mWithdrawalActivityView;

    WithdrawalService(final WithdrawalActivityView withdrawalActivityView) {
        this.mWithdrawalActivityView = withdrawalActivityView;
    }

    void getTest() {
        final WithdrawalRetrofitInterface withdrawalRetrofitInterface = getRetrofit().create(WithdrawalRetrofitInterface.class);
        withdrawalRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mWithdrawalActivityView.validateFailure(null);
                    return;
                }

                mWithdrawalActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mWithdrawalActivityView.validateFailure(null);
            }
        });
    }

}
