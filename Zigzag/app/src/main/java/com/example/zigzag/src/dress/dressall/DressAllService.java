package com.example.zigzag.src.dress.dressall;

import com.example.zigzag.src.dress.dressall.interfaces.DressAllFragmentView;
import com.example.zigzag.src.dress.dressall.interfaces.DressAllRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class DressAllService {
    private final DressAllFragmentView mDressAllFragmentView;

    DressAllService(final DressAllFragmentView dressAllFragmentView) {
        this.mDressAllFragmentView = dressAllFragmentView;
    }

    void getItemList() {
        final DressAllRetrofitInterface dressAllRetrofitInterface = getRetrofit().create(DressAllRetrofitInterface.class);
        dressAllRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mDressAllFragmentView.validateFailure(null);
                    return;
                }

                mDressAllFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mDressAllFragmentView.validateFailure(null);
            }
        });
    }
}
