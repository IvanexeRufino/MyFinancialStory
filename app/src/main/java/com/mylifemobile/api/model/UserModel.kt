package com.mylifemobile.api.model

import com.google.gson.annotations.SerializedName

data class UserModel(@SerializedName("id") var id:Int = 0,
                     @SerializedName("email") var email:String,
                     @SerializedName("password") var password: String)