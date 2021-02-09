package id.rama.weatherapps.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import id.rama.weatherapps.R
import id.rama.weatherapps.view.util.ConstantPref
import id.rama.weatherapps.view.util.SharedPrefHelper
import kotlinx.android.synthetic.main.activity_getstarted.*

class GetStarted : AppCompatActivity() {
    lateinit var sharedPref: SharedPrefHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_getstarted)

        sharedPref = SharedPrefHelper(this)
        btn_lanjutkan.setOnClickListener {
            startActivity(Intent(this, LoginGuest::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharedPref.getBoolean(ConstantPref.PREF_IS_LOGIN)) {
            startActivity(Intent(this, MenuUtama::class.java))
            finish()
        }
    }
}