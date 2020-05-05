package com.example.zigzag.src.bottom.straight;

import com.example.zigzag.src.bottom.straight.interfaces.StraightFragmentView;
import com.example.zigzag.src.bottom.straight.interfaces.StraightRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class StraightService {
    private final StraightFragmentView mStraightFragmentView;

    StraightService(final StraightFragmentView straightFragmentView) {
        this.mStraightFragmentView = straightFragmentView;
    }

    void getItemList() {
        final StraightRetrofitInterface straightRetrofitInterface = getRetrofit().create(StraightRetrofitInterface.class);
        straightRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mStraightFragmentView.validateFailure(null);
                    return;
                }

                mStraightFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mStraightFragmentView.validateFailure(null);
            }
        });
    }
}
