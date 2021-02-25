package com.mylifemobile.api

import com.mylifemobile.api.model.CategoryModel
import com.mylifemobile.api.model.TransactionModel
import com.mylifemobile.api.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @POST("transaction")
    fun createExpenses(@Body expense:TransactionModel): Call<TransactionModel>

    @POST("user")
    fun createUser(@Body expense:UserModel): Call<UserModel>

    @GET("categories")
    fun getCategories(): Call<List<CategoryModel>>

}
