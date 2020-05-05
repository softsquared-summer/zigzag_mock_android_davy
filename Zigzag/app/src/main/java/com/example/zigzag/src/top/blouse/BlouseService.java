package com.example.zigzag.src.top.blouse;

import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;
import com.example.zigzag.src.top.blouse.interfaces.BlouseFragmentView;
import com.example.zigzag.src.top.blouse.interfaces.BlouseRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class BlouseService {
    private final BlouseFragmentView mBlouseFragmentView;

    BlouseService(final BlouseFragmentView blouseFragmentView) {
        this.mBlouseFragmentView = blouseFragmentView;
    }

    void getItemList() {
        final BlouseRetrofitInterface blouseRetrofitInterface = getRetrofit().create(BlouseRetrofitInterface.class);
        blouseRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mBlouseFragmentView.validateFailure(null);
                    return;
                }

                mBlouseFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mBlouseFragmentView.validateFailure(null);
            }
        });
    }
}
