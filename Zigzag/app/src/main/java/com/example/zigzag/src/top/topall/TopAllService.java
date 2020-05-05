package com.example.zigzag.src.top.topall;

import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;
import com.example.zigzag.src.top.topall.interfaces.TopAllFragmentView;
import com.example.zigzag.src.top.topall.interfaces.TopAllRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class TopAllService {
    private final TopAllFragmentView mTopAllFragmentView;

    TopAllService(final TopAllFragmentView topAllFragmentView) {
        this.mTopAllFragmentView = topAllFragmentView;
    }

    void getItemList() {
        final TopAllRetrofitInterface topAllRetrofitInterface = getRetrofit().create(TopAllRetrofitInterface.class);
        topAllRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mTopAllFragmentView.validateFailure(null);
                    return;
                }

                mTopAllFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mTopAllFragmentView.validateFailure(null);
            }
        });
    }
}
