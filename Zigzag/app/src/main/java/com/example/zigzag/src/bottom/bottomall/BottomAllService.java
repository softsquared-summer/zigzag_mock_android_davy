package com.example.zigzag.src.bottom.bottomall;

import com.example.zigzag.src.bottom.bottomall.interfaces.BottomAllFragmentView;
import com.example.zigzag.src.bottom.bottomall.interfaces.BottomAllRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class BottomAllService {
    private final BottomAllFragmentView mBottomAllFragmentView;

    BottomAllService(final BottomAllFragmentView bottomAllFragmentView) {
        this.mBottomAllFragmentView = bottomAllFragmentView;
    }

    void getItemList() {
        final BottomAllRetrofitInterface bottomAllRetrofitInterface = getRetrofit().create(BottomAllRetrofitInterface.class);
        bottomAllRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mBottomAllFragmentView.validateFailure(null);
                    return;
                }

                mBottomAllFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mBottomAllFragmentView.validateFailure(null);
            }
        });
    }
}
