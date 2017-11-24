package com.kulsdroid.passwordmanager

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Created by KulsDroid on 11/24/2017.
 */

@IgnoreExtraProperties
class UserInfo {

    public var id: String? = null
    public var name: String? = null
    public var emailAddress: String? = null
    public var profilePicture: String? = null
    public var authToken: String? = null

}
