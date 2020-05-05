package com.example.zigzag.src.dress.dresslong;

import com.example.zigzag.src.dress.dresslong.interfaces.DressLongFragmentView;
import com.example.zigzag.src.dress.dresslong.interfaces.DressLongRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class DressLongService {
    private final DressLongFragmentView mDressLongFragmentView;

    DressLongService(final DressLongFragmentView dressLongFragmentView) {
        this.mDressLongFragmentView = dressLongFragmentView;
    }

    void getItemList() {
        final DressLongRetrofitInterface dressLongRetrofitInterface = getRetrofit().create(DressLongRetrofitInterface.class);
        dressLongRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mDressLongFragmentView.validateFailure(null);
                    return;
                }

                mDressLongFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mDressLongFragmentView.validateFailure(null);
            }
        });
    }
}
