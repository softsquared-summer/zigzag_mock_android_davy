package com.example.zigzag.src.bottom.slacks;

import com.example.zigzag.src.bottom.slacks.interfaces.SlacksFragmentView;
import com.example.zigzag.src.bottom.slacks.interfaces.SlacksRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class SlacksService {
    private final SlacksFragmentView mSlacksFragmentView;

    SlacksService(final SlacksFragmentView slacksFragmentView) {
        this.mSlacksFragmentView = slacksFragmentView;
    }

    void getItemList() {
        final SlacksRetrofitInterface slacksRetrofitInterface = getRetrofit().create(SlacksRetrofitInterface.class);
        slacksRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mSlacksFragmentView.validateFailure(null);
                    return;
                }

                mSlacksFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mSlacksFragmentView.validateFailure(null);
            }
        });
    }
}
