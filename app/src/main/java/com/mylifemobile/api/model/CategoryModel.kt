package com.mylifemobile.api.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(@SerializedName("ID") var id:Int = 0,
                         @SerializedName("name") var name:String,
                         @SerializedName("user_id") var userId:Int)