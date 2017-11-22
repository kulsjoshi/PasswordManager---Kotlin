package com.kulsdroid.passwordmanager.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.kulsdroid.passwordmanager.R

/**
 * Created by KulsDroid on 11/22/2017.
 */
class SplashScreenActivity : ParentAppCompatActivity() {

    private val TAG: String = SplashScreenActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initialization()
    }

    private fun initialization() {

        val handler = Handler()
        handler.postDelayed({loginIntent() }, 3000)

    }

    private fun loginIntent() {
        val intent = Intent(mContext, LoginActivity::class.java)
        startActivity(intent)
    }
}