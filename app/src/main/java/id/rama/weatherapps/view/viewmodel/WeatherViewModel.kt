package id.rama.weatherapps.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.weatherapps.view.model.WeatherModel
import id.rama.weatherapps.view.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

//    val myResponse : MutableLiveData<Response<WeatherModel>> = MutableLiveData()
//
//    fun getWeather(){
//        viewModelScope.launch {
//            val response = repository.getWeather()
//            myResponse.value = response
//        }
//    }
}