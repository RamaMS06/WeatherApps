package id.rama.weatherapps.view.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.bumptech.glide.Glide
import id.rama.weatherapps.R
import id.rama.weatherapps.view.api.ApiService
import id.rama.weatherapps.view.api.WeatherInstance
import id.rama.weatherapps.view.model.WeatherItem
import id.rama.weatherapps.view.model.WeatherModel
import id.rama.weatherapps.view.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_menu_utama.*
import retrofit2.Call
import retrofit2.Response
import java.net.URLEncoder

class MenuUtama : AppCompatActivity() {

//    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_menu_utama)

        getWeather()
//        val repository = WeatherRepository()
//        val weatherViewModelFactory = WeatherViewModelFactory(repository)
//        weatherViewModel = ViewModelProvider(this,weatherViewModelFactory).get(WeatherViewModel::class.java)
//        weatherViewModel.getWeather()
//        weatherViewModel.myResponse.observe(this, Observer {response ->
//               if (response.isSuccessful){
//                   Log.d("Response",response.body()?.name.toString())
//               }
//
//        })
    }

    fun getWeather(){
        val request = WeatherInstance.buildService(ApiService::class.java)
        val callJakarta = request.getWeatherData("Jakarta",getString(R.string.api_key))
        val callBandung = request.getWeatherData("Bandung",getString(R.string.api_key))
        val callSurabaya = request.getWeatherData("Surabaya",getString(R.string.api_key))

        callJakarta.enqueue(object : retrofit2.Callback<WeatherModel>{
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("Error", "Data Tidak Ada")
                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n", "CheckResult")
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                val responseModel = response.body()!!
                if (response.isSuccessful){
                    txt_lokasi1_menu_utama.text = responseModel.name
                    txt_kode_negara.text = responseModel.sys?.country
                    txt_lokasi2_menu_utama.text = responseModel.name
                    lat_long_menu_utama.text = responseModel.coord?.lat.toString() +" - "+responseModel.coord?.lon.toString()
                    txt_temperature_menu_utama.text = responseModel.main?.temp.toString()
                    txt_weather_menu_utama.text = responseModel.weather?.get(0)?.main
                    txt_isi_humidity.text = responseModel.main?.humidity.toString()
                    txt_isi_wind.text = responseModel.wind?.speed.toString()
                    txt_isi_pressure.text = responseModel.main?.pressure.toString()
                    Glide.with(this@MenuUtama)
                        .load("https://openweathermap.org/img/wn/"+responseModel.weather?.get(0)?.icon+"@2x.png")
                        .into(img_weather1_menu_utama)

                }
            }
        })

        callBandung.enqueue(object : retrofit2.Callback<WeatherModel>{
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("Error", "Data Tidak Ada")
                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n", "CheckResult")
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                val responseModel = response.body()!!
                if (response.isSuccessful){
                    txt_lokasi3_menu_utama.text = responseModel.name
                    lat_long2_menu_utama.text = responseModel.coord?.lat.toString() +" - "+responseModel.coord?.lon.toString()
                    txt_temperature2_menu_utama.text = responseModel.main?.temp.toString()
                    txt_weather2_menu_utama.text = responseModel.weather?.get(0)?.main
                    Glide.with(this@MenuUtama)
                        .load("https://openweathermap.org/img/wn/"+responseModel.weather?.get(0)?.icon+"@2x.png")
                        .into(img_weather2_menu_utama)

                }
            }
        })


        callSurabaya.enqueue(object : retrofit2.Callback<WeatherModel>{
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("Error", "Data Tidak Ada")
                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n", "CheckResult")
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                val responseModel = response.body()!!
                if (response.isSuccessful){
                    txt_lokasi4_menu_utama.text = responseModel.name
                    lat_long3_menu_utama.text = responseModel.coord?.lat.toString() +" - "+responseModel.coord?.lon.toString()
                    txt_temperature3_menu_utama.text = responseModel.main?.temp.toString()
                    txt_weather3_menu_utama.text = responseModel.weather?.get(0)?.main
                    Glide.with(this@MenuUtama)
                        .load("https://openweathermap.org/img/wn/"+responseModel.weather?.get(0)?.icon+"@2x.png")
                        .into(img_weather3_menu_utama)

                }
            }
        })



    }
}

