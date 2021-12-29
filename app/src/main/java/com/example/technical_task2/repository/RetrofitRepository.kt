package com.example.technical_task2.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.technical_task2.models.ModelListId
import com.example.technical_task2.models.ModelResult
import com.example.technical_task2.models.ModelWrapResult
import com.example.technical_task2.network.Credentials
import com.example.technical_task2.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitRepository {

    private val mutableLiveDataModelResult: MutableLiveData<ModelWrapResult<List<ModelResult>>> = MutableLiveData()
    val liveDataModelResult: LiveData<ModelWrapResult<List<ModelResult>>> = mutableLiveDataModelResult

    fun getListId() {
        var call = RetrofitClient.getInstance()

        call.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getPeople(it)
            }, {
                Log.d("MyLog", "Error list: " + it.message)
                mutableLiveDataModelResult.value = ModelWrapResult.error(it.message, null)
            })
    }

    fun getPeople(body: ModelListId?) {
        var list = body?.data?.toList()
        var retrofit = RetrofitClient.getInstance()

        Observable.fromIterable(list)
            .flatMap {
                Log.d("MyLog", "STEP")
                Observable.just(it)
                    .flatMap {
                        retrofit.getPerson(it)
                    }
                    .doOnError { Log.d("MyLog", "ERROR: " + it.message) }
                    .onErrorResumeNext(Observable.empty())

            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableLiveDataModelResult.value = ModelWrapResult.success(it)
            }, {
                Log.d("MyLog", "Error element: " + it.message)
                mutableLiveDataModelResult.value = ModelWrapResult.error(it.message, null)
            })
    }
}