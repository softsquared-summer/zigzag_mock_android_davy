package com.example.zigzag.src.outer.cardigan;

import com.example.zigzag.src.outer.cardigan.interfaces.CardiganFragmentView;
import com.example.zigzag.src.outer.cardigan.interfaces.CardiganRetrofitInterface;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class CardiganService {
    private final CardiganFragmentView mCardiganFragmentView;

    CardiganService(final CardiganFragmentView cardiganFragmentView) {
        this.mCardiganFragmentView = cardiganFragmentView;
    }


    void getItemList() {
        final CardiganRetrofitInterface cardiganRetrofitInterface = getRetrofit().create(CardiganRetrofitInterface.class);
        cardiganRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mCardiganFragmentView.validateFailure(null);
                    return;
                }

                mCardiganFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mCardiganFragmentView.validateFailure(null);
            }
        });
    }
}
