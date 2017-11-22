package com.kulsdroid.passwordmanager.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

/**
 * Created by KulsDroid on 11/22/2017.
 */

class TestClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val handler = Handler()
        handler.postDelayed({ }, 2000)

    }
}
