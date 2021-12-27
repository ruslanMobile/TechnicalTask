package com.example.technical_task2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technical_task2.models.ModelListId
import com.example.technical_task2.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository {

    val liveDataListId: MutableLiveData<ModelListId> = MutableLiveData()

    fun getListId() {
        var call = RetrofitClient.getInstance()
            .getList("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMjMiLCJpZGVudGl0eSI6IjEyMzQifQ.yOIx1ZozHSMy_ZndEEMXIH0YeGUkHH3idl_2WTI12gs")
        call?.enqueue(object : Callback<ModelListId> {
            override fun onResponse(
                call: Call<ModelListId>?,
                response: Response<ModelListId>?
            ) {
                if (response != null) {
                    liveDataListId.value = response.body()
                    getPeople(response.body())
                }
            }

            override fun onFailure(call: Call<ModelListId>?, t: Throwable?) {
                Log.d("MyLog", "onFailure " + t.toString())
            }
        })
    }

    fun getPeople(body: ModelListId?) {
        var list = body?.data?.toList()
        for (i in list!!){

        }
    }
}