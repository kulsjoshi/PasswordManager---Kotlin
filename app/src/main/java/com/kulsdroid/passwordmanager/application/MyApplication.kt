package com.kulsdroid.passwordmanager.application

import android.app.Application
import android.content.Context

/**
 * Created by KulsDroid on 11/22/2017.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }

    companion object {
        var mContext: Context? = null
    }


}


