package id.rama.weatherapps.view.api

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object WeatherInstance {
    private const val WEATHER_URL = "https://api.openweathermap.org/data/2.5/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun<T> buildService(service : Class<T>) : T{
        return retrofit.create(service)
    }

//    val apiWeather: ApiService by lazy {
//        retrofit.create(ApiService::class.java)
//    }

}