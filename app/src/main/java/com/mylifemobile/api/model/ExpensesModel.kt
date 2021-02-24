package com.mylifemobile.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ExpensesModel (@SerializedName("id") var id:Int? = null,
                          @SerializedName("amount") var amount:Number,
                          @SerializedName("date") var date: Date,
                          @SerializedName("user_id") var userId: Int,
                          @SerializedName("category_id") var categoryId: Int)