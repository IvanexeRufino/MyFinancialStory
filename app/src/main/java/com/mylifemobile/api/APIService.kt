package com.mylifemobile.api

import com.mylifemobile.api.model.ExpensesModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("transaction")
    fun createExpenses(@Body expense:ExpensesModel): Call<ExpensesModel>
}
