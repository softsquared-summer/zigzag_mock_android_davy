package com.example.zigzag.src.bottom.slacks.interfaces;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.outer.cardigan.models.ItemsResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SlacksRetrofitInterface {
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

    @GET("/items?category=바지&category_detail=슬랙스팬츠")
    Call<ItemsResponse> getItemList();
}
