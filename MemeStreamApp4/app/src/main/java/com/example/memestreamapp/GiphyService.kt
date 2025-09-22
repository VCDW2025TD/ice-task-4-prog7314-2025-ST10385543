package com.example.memestreamapp.data.network

import com.example.memestreamapp.data.model.GifResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("trending")
    suspend fun getTrending(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): GifResponse
}
