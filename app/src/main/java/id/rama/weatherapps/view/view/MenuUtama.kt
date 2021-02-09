package id.rama.weatherapps.view.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bumptech.glide.Glide
import id.rama.weatherapps.R
import id.rama.weatherapps.view.api.ApiService
import id.rama.weatherapps.view.api.WeatherInstance
import id.rama.weatherapps.view.database.UserWeatherDB
import id.rama.weatherapps.view.model.UserModel
import id.rama.weatherapps.view.model.WeatherModel
import id.rama.weatherapps.view.util.ConstantPref
import id.rama.weatherapps.view.util.SharedPrefHelper
import id.rama.weatherapps.view.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_menu_utama.*
import retrofit2.Call
import retrofit2.Response

class MenuUtama : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    lateinit var sharedPref : SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_menu_utama)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedPref = SharedPrefHelper(this)

        getDataUser()
        getWeather()
        logoutUser()
    }

    private fun logoutUser() {
        txt_logout_user.setOnClickListener {
            sharedPref.clear()
            startActivity(Intent(this,LoginGuest::class.java))
            finish()
        }
    }

    private fun getDataUser() {

        //SharedPref
        txt_nama_user.text = sharedPref.getString(ConstantPref.PREF_GUEST)

        //Masih terkendala untuk get single data, jadi saya tampilkan di log
        userViewModel.getData.observe(this, Observer { user->
            Log.d("List Data User", "Data user : " + user.toString())
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    fun getWeather() {
        val request = WeatherInstance.buildService(ApiService::class.java)
        val callJakarta = request.getWeatherData("Jakarta", getString(R.string.api_key))
        val callBandung = request.getWeatherData("Bandung", getString(R.string.api_key))
        val callSurabaya = request.getWeatherData("Surabaya", getString(R.string.api_key))

        callJakarta.enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("Error", "Data Tidak Ada")
                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n", "CheckResult")
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                val responseModel = response.body()!!
                if (response.isSuccessful) {

                    txt_lokasi2_menu_utama.text = responseModel.name
                    lat_long_menu_utama.text =
                        responseModel.coord?.lat.toString() + " - " + responseModel.coord?.lon.toString()
                    txt_temperature_menu_utama.text = responseModel.main?.temp.toString()
                    txt_weather_menu_utama.text = responseModel.weather?.get(0)?.main
                    txt_isi_humidity.text = responseModel.main?.humidity.toString()
                    txt_isi_wind.text = responseModel.wind?.speed.toString()
                    txt_isi_pressure.text = responseModel.main?.pressure.toString()
                    Glide.with(this@MenuUtama)
                        .load("https://openweathermap.org/img/wn/" + responseModel.weather?.get(0)?.icon + "@2x.png")
                        .into(img_weather1_menu_utama)

                }
            }
        })

        callBandung.enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("Error", "Data Tidak Ada")
                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n", "CheckResult")
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                val responseModel = response.body()!!
                if (response.isSuccessful) {
                    txt_lokasi3_menu_utama.text = responseModel.name
                    lat_long2_menu_utama.text =
                        responseModel.coord?.lat.toString() + " - " + responseModel.coord?.lon.toString()
                    txt_temperature2_menu_utama.text = responseModel.main?.temp.toString()
                    txt_weather2_menu_utama.text = responseModel.weather?.get(0)?.main
                    Glide.with(this@MenuUtama)
                        .load("https://openweathermap.org/img/wn/" + responseModel.weather?.get(0)?.icon + "@2x.png")
                        .into(img_weather2_menu_utama)

                }
            }
        })


        callSurabaya.enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("Error", "Data Tidak Ada")
                Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n", "CheckResult")
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                val responseModel = response.body()!!
                if (response.isSuccessful) {
                    txt_lokasi4_menu_utama.text = responseModel.name
                    lat_long3_menu_utama.text =
                        responseModel.coord?.lat.toString() + " - " + responseModel.coord?.lon.toString()
                    txt_temperature3_menu_utama.text = responseModel.main?.temp.toString()
                    txt_weather3_menu_utama.text = responseModel.weather?.get(0)?.main
                    Glide.with(this@MenuUtama)
                        .load("https://openweathermap.org/img/wn/" + responseModel.weather?.get(0)?.icon + "@2x.png")
                        .into(img_weather3_menu_utama)

                }
            }
        })


    }
}

