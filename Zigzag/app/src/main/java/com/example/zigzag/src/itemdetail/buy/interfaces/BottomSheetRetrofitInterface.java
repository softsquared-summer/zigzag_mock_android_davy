package com.example.zigzag.src.itemdetail.buy.interfaces;

import com.example.zigzag.src.itemdetail.buy.models.BucketBody;
import com.example.zigzag.src.itemdetail.buy.models.BucketResponse;
import com.example.zigzag.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BottomSheetRetrofitInterface {
//    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/basket")
    Call<BucketResponse> postBucket(@Body BucketBody params);

//    @GET("/items/{itemID}")
//    Call<ItemResponse> getItemDetail(@Path("itemID") final int number);
}
