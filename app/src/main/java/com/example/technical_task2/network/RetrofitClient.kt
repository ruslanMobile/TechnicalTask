package com.example.technical_task2.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


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
}/*
val logging = HttpLoggingInterceptor()
logging.level = HttpLoggingInterceptor.Level.BODY

val httpClient = OkHttpClient.Builder()
httpClient.addInterceptor { chain ->
    val original = chain.request()

    // Request customization: add request headers
    val requestBuilder = original.newBuilder()
        .header("Authorization", "Bearer ${Credentials.TOKEN}") // <-- this is the important line

    val request = requestBuilder.build()
    chain.proceed(request)
}


httpClient.connectTimeout(30, TimeUnit.SECONDS)
httpClient.readTimeout(30, TimeUnit.SECONDS)

httpClient.addNetworkInterceptor(logging)

val okHttpClient=httpClient.build()
val client: OkHttpClient = httpClient.build()*/