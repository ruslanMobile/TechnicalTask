package com.example.technical_task2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technical_task2.models.ModelListId
import com.example.technical_task2.models.ModelResult
import com.example.technical_task2.network.Credentials
import com.example.technical_task2.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
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
        var listResult = mutableListOf<ModelResult>()

        var retrofit = RetrofitClient.getInstance()
        Observable.fromIterable(list)
            .flatMap {
                Log.d("MyLog", "STEP")
                Observable.just(it)
                    .flatMap {
                        retrofit.getPerson("Bearer ${Credentials.TOKEN}", it)
                    }
                    .doOnError { Log.d("MyLog", "ERROR: " + it.message) }
                    .onErrorResumeNext(Observable.empty())

            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (el in it) {
                    Log.d("MyLog", "STEP sub " + el.data)
                    listResult.add(el)
                }
            }, {
                Log.d("MyLog", "Error: " + it.message)
            })
    }
}