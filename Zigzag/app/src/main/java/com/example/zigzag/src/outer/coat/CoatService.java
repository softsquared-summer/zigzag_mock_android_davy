package com.example.zigzag.src.outer.coat;

import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;
import com.example.zigzag.src.outer.coat.interfaces.CoatFragmentView;
import com.example.zigzag.src.outer.coat.interfaces.CoatRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class CoatService {
    private final CoatFragmentView mCoatFragmentView;

    CoatService(final CoatFragmentView coatFragmentView) {
        this.mCoatFragmentView = coatFragmentView;
    }

    void getItemList() {
        final CoatRetrofitInterface coatRetrofitInterface = getRetrofit().create(CoatRetrofitInterface.class);
        coatRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mCoatFragmentView.validateFailure(null);
                    return;
                }

                mCoatFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mCoatFragmentView.validateFailure(null);
            }
        });
    }
}
