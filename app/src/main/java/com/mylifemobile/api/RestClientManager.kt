package com.mylifemobile.api

import com.mylifemobile.api.model.ExpensesModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClientManager {

    private val restClient: APIService =  Retrofit.Builder()
            .baseUrl("https://my-life-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

    fun createExpense(expense: ExpensesModel): Boolean {
        val response = restClient.createExpenses(expense).execute()
        val expenseCreated = response.body() as ExpensesModel

        return expenseCreated.id != 0
    }
}