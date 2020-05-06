package com.example.zigzag.src.home.shoppingmall.shoprank;

import com.example.zigzag.src.home.shoppingmall.shoprank.interfaces.ShopRankFragmentView;
import com.example.zigzag.src.home.shoppingmall.shoprank.interfaces.ShopRankRetrofitInterface;
import com.example.zigzag.src.home.shoppingmall.shoprank.models.ShopRankResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class ShopRankService {
    private final ShopRankFragmentView mShopRankFragmentView;

    ShopRankService(final ShopRankFragmentView shopRankFragmentView) {
        this.mShopRankFragmentView = shopRankFragmentView;
    }

    void getShopRank(){
        final ShopRankRetrofitInterface shopRankRetrofitInterface = getRetrofit().create(ShopRankRetrofitInterface.class);
        shopRankRetrofitInterface.getShopRank().enqueue(new Callback<ShopRankResponse>() {
            @Override
            public void onResponse(Call<ShopRankResponse> call, Response<ShopRankResponse> response) {
                final ShopRankResponse shopRankResponse = response.body();
                if (shopRankResponse == null) {
                    mShopRankFragmentView.validateFailure(null);
                    return;
                }

                mShopRankFragmentView.getShopRankSuccess(shopRankResponse.getIsSuccess(),shopRankResponse.getCode(), shopRankResponse.getMessage(),shopRankResponse.getShopRankResults());
            }

            @Override
            public void onFailure(Call<ShopRankResponse> call, Throwable t) {
                mShopRankFragmentView.validateFailure(null);
            }
        });
    }
}
