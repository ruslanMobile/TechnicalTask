package com.example.technical_task2.network

import com.example.technical_task2.models.ModelListId
import com.example.technical_task2.models.ModelResult
import io.reactivex.Observable

import retrofit2.http.GET

import retrofit2.http.Path

interface Api {
    @GET("list")
    fun getList() : Observable<ModelListId>

    @GET("get/{id}")
    fun getPerson(@Path("id") idPerson: String) : Observable<ModelResult>
}