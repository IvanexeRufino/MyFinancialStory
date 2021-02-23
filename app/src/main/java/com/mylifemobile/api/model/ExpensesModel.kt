package com.mylifemobile.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ExpensesModel (@SerializedName("id") var id:Int = 0,
                          @SerializedName("amount") var amount:Number,
                          @SerializedName("date") var date: Date,
                          @SerializedName("user_id") var userId: Long,
                          @SerializedName("category_id") var categoryId: Long)