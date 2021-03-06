package com.mylifemobile.api

import com.google.gson.GsonBuilder
import com.mylifemobile.api.ApiWorker.restClient
import com.mylifemobile.api.model.CategoryModel
import com.mylifemobile.api.model.TransactionModel
import com.mylifemobile.api.model.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClientManager {

    fun createExpense(expense: TransactionModel): Boolean {
        val response = restClient.createExpenses(expense).execute()
        val expenseCreated = response.body() as TransactionModel

        return expenseCreated.id != 0
    }

    fun refreshBankMovements(userID: Int) {
        restClient.refreshBankMovements(userID).execute()
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


object ApiWorker {
    private var mGsonConverter: GsonConverterFactory? = null

    val restClient: APIService =  Retrofit.Builder()
        .baseUrl("https://my-life-api.herokuapp.com/")
        .addConverterFactory(ApiWorker.gsonConverter)
        .build()
        .create(APIService::class.java)

    val gsonConverter: GsonConverterFactory
        get() {
            if(mGsonConverter == null){
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                            .disableHtmlEscaping()
                            .create())
            }
            return mGsonConverter!!
        }
}