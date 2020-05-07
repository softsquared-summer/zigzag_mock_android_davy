package com.example.zigzag.src.itemdetail.buy;

import com.example.zigzag.src.itemdetail.buy.interfaces.BottomSheetActivityView;
import com.example.zigzag.src.itemdetail.buy.interfaces.BottomSheetRetrofitInterface;
import com.example.zigzag.src.itemdetail.buy.models.BucketBody;
import com.example.zigzag.src.itemdetail.buy.models.BucketResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class BottomSheetService {
    private final BottomSheetActivityView mBottomSheetActivityView;

    BottomSheetService(final BottomSheetActivityView bottomSheetActivityView) {
        this.mBottomSheetActivityView = bottomSheetActivityView;
    }
    final BottomSheetRetrofitInterface bottomSheetRetrofitInterface = getRetrofit().create(BottomSheetRetrofitInterface.class);

    void postBucket(int item_id, String color, String size, int num){

        bottomSheetRetrofitInterface.postBucket(new BucketBody(item_id,color,size,num)).enqueue(new Callback<BucketResponse>() {
            @Override
            public void onResponse(Call<BucketResponse> call, Response<BucketResponse> response) {
                final BucketResponse bucketResponse = response.body();
                if(bucketResponse == null){
                    mBottomSheetActivityView.validateFailure(null);
                    return;
                }
                mBottomSheetActivityView.bucketSuccess(bucketResponse.is_success(), bucketResponse.getCode(), bucketResponse.getMessage());
            }

            @Override
            public void onFailure(Call<BucketResponse> call, Throwable t) {
                mBottomSheetActivityView.validateFailure(null);
            }
        });

    }
//    void getItemDetail(int number) {
//        bottomSheetRetrofitInterface.getItemDetail(number).enqueue(new Callback<ItemResponse>() {
//            @Override
//            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
//
//                final ItemResponse itemResponse = response.body();
//
//
//                if (itemResponse == null) {
//
//                    mBottomSheetActivityView.validateFailure(null);
//                    return;
//                }
//                mBottomSheetActivityView.getItemDetailSuccess(itemResponse.isSuccess(),itemResponse.getCode(),itemResponse.getMessage(),itemResponse.getItemResult());
//            }
//
//
//            @Override
//            public void onFailure(Call<ItemResponse> call, Throwable t) {
//                mBottomSheetActivityView.validateFailure(null);
//                System.out.println(t);
//            }
//        });
//    }
}
