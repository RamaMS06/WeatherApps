package id.rama.weatherapps.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import id.rama.weatherapps.R
import kotlinx.android.synthetic.main.activity_getstarted.*

class GetStarted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_getstarted)

        btn_lanjutkan.setOnClickListener {
            startActivity(Intent(this, MenuUtama::class.java))
        }
    }
}