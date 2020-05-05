package com.example.zigzag.src.home.zigzag;

import com.example.zigzag.src.home.zigzag.interfaces.ZigzagFragmentView;
import com.example.zigzag.src.home.zigzag.interfaces.ZigzagRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class ZigzagService {
    private final ZigzagFragmentView mZigzagFragmentView;

    ZigzagService(final ZigzagFragmentView zigzagFragmentView) {
        this.mZigzagFragmentView = zigzagFragmentView;
    }

    void getItemList() {
        final ZigzagRetrofitInterface zigzagRetrofitInterface = getRetrofit().create(ZigzagRetrofitInterface.class);
        zigzagRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mZigzagFragmentView.validateFailure(null);
                    return;
                }

                mZigzagFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mZigzagFragmentView.validateFailure(null);
            }
        });
    }
}
