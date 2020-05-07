package com.example.zigzag.src.bucket;


import com.example.zigzag.src.bucket.interfaces.BucketActivityView;
import com.example.zigzag.src.bucket.interfaces.BucketRetrofitInterface;
import com.example.zigzag.src.bucket.models.BucketResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class BucketService {
    private final BucketActivityView mBucketActivityView;

    BucketService(final BucketActivityView bucketActivityView) {
        this.mBucketActivityView = bucketActivityView;
    }

    void getBucketList() {
        final BucketRetrofitInterface bucketRetrofitInterface = getRetrofit().create(BucketRetrofitInterface.class);

        bucketRetrofitInterface.getBucketList().enqueue(new Callback<BucketResponse>() {
            @Override
            public void onResponse(Call<BucketResponse> call, Response<BucketResponse> response) {

                final BucketResponse bucketResponse = response.body();

                if (bucketResponse == null) {
                    mBucketActivityView.validateFailure(null);
                    return;
                }
                mBucketActivityView.getBucketSuccess(bucketResponse.isSuccess(),bucketResponse.getCode(),bucketResponse.getMessage(),
                        bucketResponse.getBucketResult().get(0).getNum(),bucketResponse.getBucketResult().get(0).getList());
            }


            @Override
            public void onFailure(Call<BucketResponse> call, Throwable t) {
                mBucketActivityView.validateFailure(null);
            }
        });
    }
}
