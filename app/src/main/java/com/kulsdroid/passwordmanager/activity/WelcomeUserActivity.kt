package com.kulsdroid.passwordmanager.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.kulsdroid.passwordmanager.R
import kotlinx.android.synthetic.main.activity_welcome_user.*

/**
 * Created by KulsDroid on 11/22/2017.
 */
class WelcomeUserActivity : ParentAppCompatActivity() {

    private val TAG: String = WelcomeUserActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_user)
        initialization()
    }

    private fun initialization() {
        txtWelcomeName.text = mSharedPreference.userGoogleAccountName
        txtWelcomeEmail.text = mSharedPreference.userGoogleAccountEmailAddress
        Glide.with(mContext).load(mSharedPreference.userGoogleAccountProfilePicture).into(imgWelcomeProfilePic)
    }

}