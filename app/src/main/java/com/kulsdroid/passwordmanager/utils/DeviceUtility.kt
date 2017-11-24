package com.kulsdroid.passwordmanager.utils

import android.content.Context
import android.provider.Settings
import android.telephony.TelephonyManager
import com.kulsdroid.passwordmanager.application.MyApplication

/**
 * Created by KulsDroid on 11/24/2017.
 */
class DeviceUtility {

    companion object {

        fun getDeviceModel(): String {
            return android.os.Build.MODEL
        }

        fun getDeviceOS(): String {
            return System.getProperty("os.version")
        }

        fun getDeviceAPILevel(): Int {
            return android.os.Build.VERSION.SDK_INT
        }

        fun getDeviceInfo(): String {
            return android.os.Build.DEVICE
        }

        fun getDeviceProductInfo(): String {
            return android.os.Build.PRODUCT
        }

        fun getDeviceIMEI(): String? {
            var deviceUniqueIdentifier: String? = null
            val tm = MyApplication.mContext!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (null != tm) {
                deviceUniqueIdentifier = tm.deviceId
            }
            if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length) {
                deviceUniqueIdentifier = Settings
                        .Secure.getString(MyApplication.mContext!!.getContentResolver(),
                        Settings.Secure.ANDROID_ID)
            }
            return deviceUniqueIdentifier
        }
    }

}