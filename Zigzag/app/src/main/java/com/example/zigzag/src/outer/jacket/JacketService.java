package com.example.zigzag.src.outer.jacket;

import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;
import com.example.zigzag.src.outer.jacket.interfaces.JacketFragmentView;
import com.example.zigzag.src.outer.jacket.interfaces.JacketRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class JacketService {
    private final JacketFragmentView mJacketFragmentView;

    JacketService(final JacketFragmentView jacketFragmentView) {
        this.mJacketFragmentView = jacketFragmentView;
    }

    void getItemList() {
        final JacketRetrofitInterface jacketRetrofitInterface = getRetrofit().create(JacketRetrofitInterface.class);
        jacketRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                final ItemsResponse itemsAllResponse = response.body();
                if (itemsAllResponse == null) {
                    mJacketFragmentView.validateFailure(null);
                    return;
                }

                mJacketFragmentView.getItemSuccess(itemsAllResponse.getIsSuccess(),itemsAllResponse.getCode(), itemsAllResponse.getMessage(),itemsAllResponse.getItemsResults());
            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mJacketFragmentView.validateFailure(null);
            }
        });
    }
}
