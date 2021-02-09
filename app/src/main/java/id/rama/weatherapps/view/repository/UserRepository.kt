package id.rama.weatherapps.view.repository

import androidx.lifecycle.LiveData
import id.rama.weatherapps.view.api.WeatherInstance
import id.rama.weatherapps.view.dao.UserDao
import id.rama.weatherapps.view.model.UserModel
import id.rama.weatherapps.view.model.WeatherModel
import retrofit2.Call
import retrofit2.Response

class UserRepository(private val userDao: UserDao) {

    val getData: LiveData<List<UserModel>> = userDao.getData()

   suspend fun addUser(userModel: UserModel){
        userDao.addUser(userModel)
    }



}