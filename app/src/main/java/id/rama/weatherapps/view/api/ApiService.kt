package id.rama.weatherapps.view.api

import id.rama.weatherapps.view.model.WeatherModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun getWeatherData(
        @Query("q") query: String,
        @Query("appid") appid: String
    ): Call<WeatherModel>
}