package com.faizmokhtar.weatherapp.api

import com.faizmokhtar.weatherapp.model.CurrentWeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {
    @GET("weather")
    fun currentWeather(
        @Query("q") city: String,
        @Query("appid") key: String
    ) : Call<CurrentWeatherData>

}
