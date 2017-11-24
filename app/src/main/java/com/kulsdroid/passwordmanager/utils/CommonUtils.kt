package com.kulsdroid.passwordmanager.utils

import android.widget.Toast
import com.kulsdroid.passwordmanager.application.MyApplication


/**
 * Created by KulsDroid on 11/22/2017.
 */

fun showToast(message: String) {
    Toast.makeText(MyApplication.mContext, message, Toast.LENGTH_SHORT).show();
}


