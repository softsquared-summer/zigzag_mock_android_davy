package com.example.zigzag.src.outer.all;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.outer.all.interfaces.AllFragmentView;
import com.example.zigzag.src.outer.all.interfaces.AllRetrofitInterface;
import com.example.zigzag.src.outer.all.models.OuterAllResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class AllService {
    private final AllFragmentView mAllFragmentView;

    AllService(final AllFragmentView allFragmentView) {
        this.mAllFragmentView = allFragmentView;
    }

    void getTest() {
        final AllRetrofitInterface allRetrofitInterface = getRetrofit().create(AllRetrofitInterface.class);
        allRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mAllFragmentView.validateFailure(null);
                    return;
                }

                mAllFragmentView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mAllFragmentView.validateFailure(null);
            }
        });
    }

    void getItemList() {
        final AllRetrofitInterface allRetrofitInterface = getRetrofit().create(AllRetrofitInterface.class);
        allRetrofitInterface.getItemList().enqueue(new Callback<OuterAllResponse>() {
            @Override
            public void onResponse(Call<OuterAllResponse> call, Response<OuterAllResponse> response) {
                final OuterAllResponse outerAllResponse = response.body();
                if (outerAllResponse == null) {
                    mAllFragmentView.validateFailure(null);
                    return;
                }

                mAllFragmentView.getItemSuccess(outerAllResponse.getIsSuccess(),outerAllResponse.getCode(), outerAllResponse.getMessage(),outerAllResponse.getOuterAllResults());
            }

            @Override
            public void onFailure(Call<OuterAllResponse> call, Throwable t) {
                mAllFragmentView.validateFailure(null);
            }
        });
    }
}
