package com.example.zigzag.src.signIn.interfaces;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.signIn.models.SignInBody;
import com.example.zigzag.src.signIn.models.SignInResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignInRetrofitInterface {
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

    @POST("/user/toke")
    Call<SignInResponse> SignInTest(@Body SignInBody params);
}
