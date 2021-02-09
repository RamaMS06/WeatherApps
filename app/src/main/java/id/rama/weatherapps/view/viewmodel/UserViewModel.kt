package id.rama.weatherapps.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.weatherapps.view.database.UserWeatherDB
import id.rama.weatherapps.view.model.UserModel
import id.rama.weatherapps.view.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val getData : LiveData<List<UserModel>>
    private val repository: UserRepository

    init {
        val userDao = UserWeatherDB.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getData = repository.getData
    }

    fun addUser(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(userModel)
        }
    }




}