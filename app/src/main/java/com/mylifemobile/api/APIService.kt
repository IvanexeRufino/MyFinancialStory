package com.mylifemobile.api

import com.mylifemobile.api.model.CategoryModel
import com.mylifemobile.api.model.TransactionModel
import com.mylifemobile.api.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {

    @POST("transaction")
    fun createExpenses(@Body expense:TransactionModel): Call<TransactionModel>

    @POST("user/{id}/refreshBank")
    fun refreshBankMovements(@Path("id") userID:Int): Call<ResponseBody>

    @POST("user")
    fun createUser(@Body expense:UserModel): Call<UserModel>

    @GET("categories")
    fun getCategories(): Call<List<CategoryModel>>

}
