package com.kulsdroid.passwordmanager.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kulsdroid.passwordmanager.utils.SharedPreferenceManager

/**
 * Created by KulsDroid on 11/22/2017.
 */
open class ParentAppCompatActivity : AppCompatActivity() {

    public lateinit var mContext: Context
    public lateinit var mSharedPreference: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialization()

    }

    private fun initialization() {

        mContext = this@ParentAppCompatActivity
        mSharedPreference = SharedPreferenceManager()

    }

}