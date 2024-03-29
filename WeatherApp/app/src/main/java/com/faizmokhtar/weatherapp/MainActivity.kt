package com.faizmokhtar.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.faizmokhtar.weatherapp.api.UnsplashAPIService
import com.faizmokhtar.weatherapp.api.WeatherAPIService
import com.faizmokhtar.weatherapp.model.CurrentImageData
import com.faizmokhtar.weatherapp.model.CurrentWeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    private val unsplashRetrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    private val weatherAPIService by lazy { retrofit.create(WeatherAPIService::class.java) }
    private val unsplashAPIService by lazy { unsplashRetrofit.create(UnsplashAPIService::class.java) }

    private val imageLoader: ImageLoader by lazy { GlideImageLoader(this) }

    private val currentLocationText: TextView by lazy { findViewById(R.id.current_location) }
    private val currentWeatherText: TextView by lazy { findViewById(R.id.current_weather) }
    private val currentTemperatureText: TextView by lazy { findViewById(R.id.current_temperature) }
    private val currentImage: ImageView by lazy { findViewById(R.id.current_image) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        val call = weatherAPIService.currentWeather("kuala lumpur", WEATHER_KEY)
        call.enqueue(object: Callback<CurrentWeatherData> {
            override fun onFailure(call: Call<CurrentWeatherData>, t: Throwable) {
                Log.e("MainActivity", "Failed to get search results", t)
            }

            override fun onResponse(call: Call<CurrentWeatherData>, response: Response<CurrentWeatherData>) {
                if (response.isSuccessful) {
                    val results = response.body()
                    getCurrentImage(results?.weather?.firstOrNull()?.description ?: "sunny")
                    currentLocationText.text = results?.name ?: "Unable to fetch Location"
                    val currentTemperature = kelvinToCelcius(results?.main?.feelsLike)
                    currentWeatherText.text = results?.weather?.firstOrNull()?.description?.capitalize()
                            ?: "Unable to fetch weather"
                    currentTemperatureText.text = "It feels like $currentTemperature °C"
                } else {
                    Log.e("MainActivity", "Failed to get search results\n ${response.errorBody()?.string() ?: ""}")
                }
            }
        })
    }

    private fun getCurrentImage(description: String) {
        val call = unsplashAPIService.currentImage(description, 1, "squarish", "Client-ID $UNSPLASH_KEY")
        call.enqueue(object: Callback<CurrentImageData> {
            override fun onFailure(call: Call<CurrentImageData>, t: Throwable) {
                Log.e("MainActivity", "Failed to get search results", t)
            }

            override fun onResponse(call: Call<CurrentImageData>, response: Response<CurrentImageData>) {
                if (response.isSuccessful) {
                    val results = response.body()
                    val currentImageUrl = results?.results?.firstOrNull()?.urls?.thumb ?: ""
                    if (currentImageUrl.isNotBlank()) {
                        imageLoader.loadImage(currentImageUrl, currentImage)
                    } else {
                        Log.d("MainActivity", "Missing Image URL")
                    }
                } else {
                    Log.e("MainActivity", "Failed to get image results\n ${response.errorBody()?.string() ?: ""}")
                }
            }
        })
    }

    private fun kelvinToCelcius(value: Double?): Double {
        return value?.minus(273.15) ?: 0.0
    }

    companion object {
        const val WEATHER_KEY = BuildConfig.API_WEATHER_KEY
        const val UNSPLASH_KEY = BuildConfig.API_UNSPLASH_KEY
    }
}
