package com.kulsdroid.passwordmanager.application

import android.app.Application
import android.content.Context
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by KulsDroid on 11/22/2017.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    companion object {
        var mContext: Context? = null
        var mFirebaseDatabase:FirebaseDatabase? = null
    }


}


