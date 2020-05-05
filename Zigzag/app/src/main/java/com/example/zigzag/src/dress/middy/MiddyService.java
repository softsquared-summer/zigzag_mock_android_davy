package com.example.zigzag.src.dress.middy;

import com.example.zigzag.src.dress.middy.interfaces.MiddyFragmentView;
import com.example.zigzag.src.dress.middy.interfaces.MiddyRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class MiddyService {
    private final MiddyFragmentView mMiddyFragmentView;

    MiddyService(final MiddyFragmentView middyFragmentView) {
        this.mMiddyFragmentView = middyFragmentView;
    }

    void getItemList() {
        final MiddyRetrofitInterface middyRetrofitInterface = getRetrofit().create(MiddyRetrofitInterface.class);
        middyRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mMiddyFragmentView.validateFailure(null);
                    return;
                }

                mMiddyFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mMiddyFragmentView.validateFailure(null);
            }
        });
    }
}
