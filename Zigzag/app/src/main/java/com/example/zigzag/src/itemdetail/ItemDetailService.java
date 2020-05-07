package com.example.zigzag.src.itemdetail;

import com.example.zigzag.src.itemdetail.interfaces.ItemDetailActivityView;
import com.example.zigzag.src.itemdetail.interfaces.ItemDetailRetrofitInterface;
import com.example.zigzag.src.itemdetail.models.ItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class ItemDetailService {
    private final ItemDetailActivityView mItemDetailActivityView;

    ItemDetailService(final ItemDetailActivityView itemDetailActivityView) {
        this.mItemDetailActivityView = itemDetailActivityView;
    }

    void getItemDetail(int number) {
        final ItemDetailRetrofitInterface itemDetailRetrofitInterface = getRetrofit().create(ItemDetailRetrofitInterface.class);


        itemDetailRetrofitInterface.getItemDetail(number).enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {


                final ItemResponse itemResponse = response.body();

                if (itemResponse == null) {

                    mItemDetailActivityView.validateFailure(null);
                    return;
                }
                mItemDetailActivityView.getItemDetailSuccess(itemResponse.isSuccess(),itemResponse.getCode(),itemResponse.getMessage(),itemResponse.getItemResult());
            }


            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                mItemDetailActivityView.validateFailure(null);
                System.out.println(t);
            }
        });
    }
}
