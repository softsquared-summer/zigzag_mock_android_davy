package com.example.zigzag.src.order;


import com.example.zigzag.src.bucket.models.BucketResponse;
import com.example.zigzag.src.order.interfaces.OrderActivityView;
import com.example.zigzag.src.order.interfaces.OrderRetrofitInterface;
import com.example.zigzag.src.order.models.AddressBody;
import com.example.zigzag.src.order.models.AddressResponse;
import com.example.zigzag.src.order.models.PaymentBody;
import com.example.zigzag.src.order.models.PaymentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;

class OrderService {
    private final OrderActivityView mOrderActivityView;

    OrderService(final OrderActivityView orderActivityView) {
        this.mOrderActivityView = orderActivityView;
    }

    void getBucketList() {
        final OrderRetrofitInterface orderRetrofitInterface = getRetrofit().create(OrderRetrofitInterface.class);

        orderRetrofitInterface.getBucketList().enqueue(new Callback<BucketResponse>() {
            @Override
            public void onResponse(Call<BucketResponse> call, Response<BucketResponse> response) {

                final BucketResponse bucketResponse = response.body();

                if (bucketResponse == null) {
                    mOrderActivityView.validateFailure(null);
                    return;
                }
                mOrderActivityView.getBucketSuccess(bucketResponse.isSuccess(),bucketResponse.getCode(),bucketResponse.getMessage(),
                        bucketResponse.getBucketResult().get(0).getNum(),bucketResponse.getBucketResult().get(0).getList());
            }


            @Override
            public void onFailure(Call<BucketResponse> call, Throwable t) {
                mOrderActivityView.validateFailure(null);
            }
        });
    }
    void postAddress(String name, String phone, int zipcode, String address, String address_detail, String memo) {
        final OrderRetrofitInterface orderRetrofitInterface = getRetrofit().create(OrderRetrofitInterface.class);
        orderRetrofitInterface.postAddress(new AddressBody(name, phone,zipcode,address,address_detail,memo)).enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                final AddressResponse addressResponse = response.body();
                if (addressResponse == null) {
                    mOrderActivityView.validateFailure(null);
                    return;
                }

                mOrderActivityView.postAddressSuccess(addressResponse.getIsSuccess(), addressResponse.getCode(), addressResponse.getMessage());
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                mOrderActivityView.validateFailure(null);
            }
        });
    }

    void postPayment(String isOver14, String isServiceAgree, String isOrderAgree, int itemId1, int itemId2, int itemId3, int itemId4, int itemId5){
        final OrderRetrofitInterface orderRetrofitInterface = getRetrofit().create(OrderRetrofitInterface.class);
        orderRetrofitInterface.postPayment(new PaymentBody(isOver14, isServiceAgree, isOrderAgree, itemId1, itemId2, itemId3, itemId4, itemId5)).enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                final PaymentResponse paymentResponse = response.body();
                if (paymentResponse == null) {
                    mOrderActivityView.validateFailure(null);
                    return;
                }

                mOrderActivityView.postPaymentSuccess(paymentResponse.getIsSuccess(), paymentResponse.getCode(), paymentResponse.getMessage());
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                mOrderActivityView.validateFailure(null);
            }
        });
    }
}
