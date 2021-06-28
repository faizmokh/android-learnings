package com.faizmokhtar.weatherapp.api

import com.faizmokhtar.weatherapp.model.CurrentImageData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashAPIService {
    @GET("search/photos")
    fun currentImage(
        @Query("query") query: String,
        @Query("per_page") quantity: Int,
        @Query("orientation") orientation: String,
        @Header("Authorization") authorization: String,
    ) : Call<CurrentImageData>
}
