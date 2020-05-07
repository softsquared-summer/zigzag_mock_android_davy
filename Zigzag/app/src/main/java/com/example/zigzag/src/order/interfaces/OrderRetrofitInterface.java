package com.example.zigzag.src.order.interfaces;


import com.example.zigzag.src.bucket.models.BucketResponse;
import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.order.models.AddressBody;
import com.example.zigzag.src.order.models.AddressResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderRetrofitInterface {
//    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<DefaultResponse> postTest(@Body RequestBody params);

    @GET("/baskets")
    Call<BucketResponse>getBucketList();

    @POST("/addrss")
    Call<AddressResponse>postAddress(@Body AddressBody params);
}
