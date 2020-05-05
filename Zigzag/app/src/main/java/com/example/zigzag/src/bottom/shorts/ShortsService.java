package com.example.zigzag.src.bottom.shorts;

import com.example.zigzag.src.bottom.shorts.interfaces.ShortsFragmentView;
import com.example.zigzag.src.bottom.shorts.interfaces.ShortsRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class ShortsService {
    private final ShortsFragmentView mShortsFragmentView;

    ShortsService(final ShortsFragmentView shortsFragmentView) {
        this.mShortsFragmentView = shortsFragmentView;
    }

    void getItemList() {
        final ShortsRetrofitInterface shortsRetrofitInterface = getRetrofit().create(ShortsRetrofitInterface.class);
        shortsRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mShortsFragmentView.validateFailure(null);
                    return;
                }

                mShortsFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mShortsFragmentView.validateFailure(null);
            }
        });
    }
}
