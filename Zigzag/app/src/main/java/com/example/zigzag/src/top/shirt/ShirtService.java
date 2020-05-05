package com.example.zigzag.src.top.shirt;

import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;
import com.example.zigzag.src.top.shirt.interfaces.ShirtFragmentView;
import com.example.zigzag.src.top.shirt.interfaces.ShirtRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class ShirtService {
    private final ShirtFragmentView mShirtFragmentView;

    ShirtService(final ShirtFragmentView shirtFragmentView) {
        this.mShirtFragmentView = shirtFragmentView;
    }

    void getItemList() {
        final ShirtRetrofitInterface shirtRetrofitInterface = getRetrofit().create(ShirtRetrofitInterface.class);
        shirtRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mShirtFragmentView.validateFailure(null);
                    return;
                }

                mShirtFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mShirtFragmentView.validateFailure(null);
            }
        });
    }
}
