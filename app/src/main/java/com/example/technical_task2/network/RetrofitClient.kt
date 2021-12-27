package com.example.technical_task2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Api? = null

    @Synchronized
    fun getInstance(): Api {
        if (instance == null)
            instance = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Credentials.BASE_URL)
                .build()
                .create(Api::class.java)
        return instance as Api
    }
}