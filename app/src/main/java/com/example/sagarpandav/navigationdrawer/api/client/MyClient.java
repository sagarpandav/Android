package com.example.sagarpandav.navigationdrawer.api.client;

import com.example.sagarpandav.navigationdrawer.api.response.GoogleDirectionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PANDAV on 02-04-2018.
 */

public interface MyClient {

    @GET("directions/json")
    Call<GoogleDirectionResponse> getDirections(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);
}
