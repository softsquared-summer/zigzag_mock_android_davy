package com.example.zigzag.src.top.tshirt;

import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;
import com.example.zigzag.src.top.tshirt.interfaces.TShirtFragmentView;
import com.example.zigzag.src.top.tshirt.interfaces.TShirtRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class TShirtService {
    private final TShirtFragmentView mTShirtFragmentView;

    TShirtService(final TShirtFragmentView tShirtFragmentView) {
        this.mTShirtFragmentView = tShirtFragmentView;
    }

    void getItemList() {
        final TShirtRetrofitInterface tShirtRetrofitInterface = getRetrofit().create(TShirtRetrofitInterface.class);
        tShirtRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mTShirtFragmentView.validateFailure(null);
                    return;
                }

                mTShirtFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mTShirtFragmentView.validateFailure(null);
            }
        });
    }
}
