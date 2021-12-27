package com.example.technical_task2.network

import com.example.technical_task2.models.ModelListId
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {
    @GET("list")
    fun getList(@Header("Authorization") token:String) : Call<ModelListId>
}