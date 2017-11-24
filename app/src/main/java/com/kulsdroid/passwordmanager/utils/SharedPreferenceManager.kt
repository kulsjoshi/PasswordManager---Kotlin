package com.kulsdroid.passwordmanager.utils

import android.content.Context
import android.content.SharedPreferences
import com.kulsdroid.passwordmanager.application.MyApplication

/**
 * Created by KulsDroid on 11/22/2017.
 */
class SharedPreferenceManager() {

    private var mSharedPreference: SharedPreferences

    init {
        mSharedPreference = MyApplication.mContext!!.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun addString(key: String, value: String) {
        mSharedPreference.edit().putString(key, value).commit()
    }

    private fun getString(key: String): String {
        return mSharedPreference.getString(key, "").toString()
    }

    fun storeGoogleCredentials(userName: String,
                               userEmail: String,
                               userAuthToken: String,
                               userId: String,
                               userProfilePicture: String,
                               userFirebaseId: String) {

        userGoogleAccountName = userName
        userGoogleAccountEmailAddress = userEmail
        userGoogleAccountAuthToken = userAuthToken
        userGoogleAccountID = userId
        userGoogleAccountProfilePicture = userProfilePicture
        userFirebaseID = userFirebaseId

    }

    var userGoogleAccountName: String
        get() = getString(PREFS_NAME)
        set(value) = addString(PREFS_NAME, value)

    var userGoogleAccountEmailAddress: String
        get() = getString(PREFS_EMAIL)
        set(value) = addString(PREFS_EMAIL, value)

    var userGoogleAccountAuthToken: String
        get() = getString(PREFS_AUTH_TOKEN)
        set(value) = addString(PREFS_AUTH_TOKEN, value)

    var userGoogleAccountID: String
        get() = getString(PREFS_USER_ID)
        set(value) = addString(PREFS_USER_ID, value)

    var userGoogleAccountProfilePicture: String
        get() = getString(PREFS_PICTURE_URL)
        set(value) = addString(PREFS_PICTURE_URL, value)

    var userFirebaseID: String
        get() = getString(PREFS_FIREBASE_USER_ID)
        set(value) = addString(PREFS_FIREBASE_USER_ID, value)
}