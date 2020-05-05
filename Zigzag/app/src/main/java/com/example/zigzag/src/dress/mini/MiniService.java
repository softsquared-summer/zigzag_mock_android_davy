package com.example.zigzag.src.dress.mini;

import com.example.zigzag.src.dress.mini.interfaces.MiniFragmentView;
import com.example.zigzag.src.dress.mini.interfaces.MiniRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class MiniService {
    private final MiniFragmentView mMiniFragmentView;

    MiniService(final MiniFragmentView miniFragmentView) {
        this.mMiniFragmentView = miniFragmentView;
    }

    void getItemList() {
        final MiniRetrofitInterface miniRetrofitInterface = getRetrofit().create(MiniRetrofitInterface.class);
        miniRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mMiniFragmentView.validateFailure(null);
                    return;
                }

                mMiniFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mMiniFragmentView.validateFailure(null);
            }
        });
    }
}
