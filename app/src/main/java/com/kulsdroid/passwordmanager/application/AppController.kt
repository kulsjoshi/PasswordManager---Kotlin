package com.kulsdroid.passwordmanager.application

import android.app.Application
import android.content.Context

/**
 * Created by KulsDroid on 11/22/2017.
 */

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {

        var appContext: Context? = null
            private set
    }

}
