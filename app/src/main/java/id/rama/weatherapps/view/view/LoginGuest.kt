package  id.rama.weatherapps.view.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.rama.weatherapps.R
import id.rama.weatherapps.view.database.UserWeatherDB
import id.rama.weatherapps.view.model.UserModel
import id.rama.weatherapps.view.util.ConstantPref
import id.rama.weatherapps.view.util.SharedPrefHelper
import id.rama.weatherapps.view.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login_guest.*

class LoginGuest : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    lateinit var sharedPref :SharedPrefHelper
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login_guest)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedPref= SharedPrefHelper(this)
        btn_login_user.setOnClickListener {
            insertData()
        }

    }


    private fun insertData() {
        val nama_panjang = edt_nama_panjang_login.text.toString()

        if (nama_panjang.isNotEmpty()){
            val user = UserModel(0, nama_panjang)
            sharedPref.putString(ConstantPref.PREF_GUEST,nama_panjang)
            sharedPref.putBoolean(ConstantPref.PREF_IS_LOGIN,true)
            userViewModel.addUser(user)
            Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MenuUtama::class.java))
            finish()
        }else{
            Toast.makeText(this, "Isi Data Terlebih Dulu!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(nama_panjang: String): Boolean {
        return !(TextUtils.isEmpty(nama_panjang))
    }
}