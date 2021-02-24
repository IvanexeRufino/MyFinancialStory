package com.mylifemobile.api

import com.mylifemobile.api.model.CategoryModel
import com.mylifemobile.api.model.ExpensesModel
import com.mylifemobile.api.model.UserModel
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

    fun createUser(user: UserModel): UserModel {
        val response = restClient.createUser(user).execute()
        return response.body() as UserModel
    }

    fun getCategories(): List<CategoryModel> {
        val response = restClient.getCategories().execute()
        return response.body() as List<CategoryModel>
    }
}