package com.kulsdroid.passwordmanager.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.kulsdroid.passwordmanager.R
import com.kulsdroid.passwordmanager.UserInfo
import com.kulsdroid.passwordmanager.application.AppController
import com.kulsdroid.passwordmanager.application.MyApplication
import com.kulsdroid.passwordmanager.utils.KEY_USERS
import com.kulsdroid.passwordmanager.utils.showToast
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by KulsDroid on 11/22/2017.
 */
class LoginActivity : ParentAppCompatActivity() {

    private val TAG: String = LoginActivity::class.java.simpleName
    private val REQUEST_GOOGLE_SIGN_IN: Int = 9001

    private var googleSignInOption: GoogleSignInOptions? = null;
    private var googleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        initialization()
    }

    private fun initialization() {

        googleSignInOption = getGoogleSignInOption();

        googleSignInButton.setSize(SignInButton.SIZE_WIDE)
        googleSignInButton.setOnClickListener { signInGoogle() }

    }

    /**
     * This method will return result of Google sign in data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_GOOGLE_SIGN_IN) {
            var task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            var mGoogleSignInAccount: GoogleSignInAccount = task.result
            handleGoogleSignInResult(mGoogleSignInAccount)
        }

    }

    /**
     * This function will generate GoogleSignInOptions object
     */
    private fun getGoogleSignInOption(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    /**
     * This function will generate GoogleSignInClient object
     */
    private fun getGoogleSignInClient(): GoogleSignInClient {
        return GoogleSignIn.getClient(MyApplication.mContext!!, getGoogleSignInOption())
    }

    /**
     * It opens gmail account selector dialog
     */
    private fun signInGoogle() {
        var mIntent: Intent
        mIntent = getGoogleSignInClient().signInIntent;
        startActivityForResult(mIntent, REQUEST_GOOGLE_SIGN_IN)
    }

    /**
     * It updates UI whether it needs to be updated or not
     */
    private fun updateUI(isSignedIn: Boolean) {

        if (isSignedIn) {
            linearProfile.visibility = View.VISIBLE
        } else {
            linearProfile.visibility = View.GONE
        }
        AppController.appContext
    }

    /**
     * It handles and show data in UI and it will save as well
     */
    private fun handleGoogleSignInResult(mGoogleSignInAccount: GoogleSignInAccount) {

        if (mGoogleSignInAccount != null) {

            var userInfo: UserInfo? = UserInfo()

            userInfo!!.name = mGoogleSignInAccount.displayName.toString()
            userInfo!!.profilePicture = mGoogleSignInAccount.photoUrl.toString()
            userInfo!!.emailAddress = mGoogleSignInAccount.email.toString();
            userInfo!!.authToken = mGoogleSignInAccount.idToken.toString()
            userInfo!!.id = mGoogleSignInAccount.id.toString()

            storeUserDataIntoFirebase(userInfo)

            updateUI(true)

        } else {
            updateUI(false)
        }

    }

    private fun moveToWelcomeScreen() {
        val mIntent = Intent(mContext, WelcomeUserActivity::class.java)
        startActivity(mIntent)
    }

    private fun getDeviceInformation() {


    }

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseUserID: String

    private fun storeUserDataIntoFirebase(userInfo: UserInfo) {

        mDatabaseReference = MyApplication.mFirebaseDatabase!!.getReference(KEY_USERS)

        mFirebaseUserID = mDatabaseReference.push().key
        mDatabaseReference.setValue(userInfo)

        mDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.e(TAG,"Error >> " + p0.toString())
                showToast("onCancelled")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                var mUserInfo: UserInfo? = dataSnapshot!!.getValue(UserInfo::class.java)

                mSharedPreference.storeGoogleCredentials(
                        mUserInfo!!.name!!, mUserInfo!!.emailAddress!!, mUserInfo!!.authToken!!,
                        mUserInfo!!.id!!, mUserInfo!!.profilePicture!!, mFirebaseUserID!!)

                showToast("Data saved!")
                moveToWelcomeScreen()
            }

        });

//        mSharedPreference.storeGoogleCredentials(personName, personEmail, personIdToken,
//                personID, personPhotoURL)
//        moveToWelcomeScreen()

    }
}