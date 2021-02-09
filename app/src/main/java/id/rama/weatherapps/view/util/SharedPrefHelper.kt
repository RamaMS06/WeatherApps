package id.rama.weatherapps.view.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context : Context) {

    private val PREFS_NAMA = "sharedPrefsUser"
    private var sharedPref : SharedPreferences
    val editor : SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAMA, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun putString(key:String, values: String){
        editor.putString(key,values).apply()
    }

    fun getString(key: String) : String?{
        return sharedPref.getString(key,null)
    }

    fun putBoolean(key: String, values: Boolean){
        editor.putBoolean(key,values).apply()
    }

    fun getBoolean(key: String) : Boolean{
        return sharedPref.getBoolean(key,false)
    }

    fun clear(){
        editor.clear()
            .apply()
    }
}