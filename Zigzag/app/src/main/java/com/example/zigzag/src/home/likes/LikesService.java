package com.example.zigzag.src.home.likes;

import com.example.zigzag.src.home.likes.interfaces.LikesFragmentView;
import com.example.zigzag.src.home.likes.interfaces.LikesRetrofitInterface;
import com.example.zigzag.src.home.likes.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class LikesService {
    private final LikesFragmentView mLikesFragmentView;

    LikesService(final LikesFragmentView likesFragmentView) {
        this.mLikesFragmentView = likesFragmentView;
    }

    void getItemList() {
        final LikesRetrofitInterface likesRetrofitInterface = getRetrofit().create(LikesRetrofitInterface.class);
        likesRetrofitInterface.getLikesItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsResponse = response.body();
                if (itemsResponse == null) {
                    mLikesFragmentView.validateFailure(null);
                    return;
                }

                mLikesFragmentView.getItemSuccess(itemsResponse.getIsSuccess(),itemsResponse.getCode(), itemsResponse.getMessage(), itemsResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mLikesFragmentView.validateFailure(null);
            }
        });
    }
}
