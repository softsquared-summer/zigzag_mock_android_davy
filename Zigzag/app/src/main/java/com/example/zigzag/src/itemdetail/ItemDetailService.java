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

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final ItemResponse itemResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (itemResponse == null) {

                    mItemDetailActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
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
