package com.example.technical_task2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.technical_task2.models.ModelResult
import com.example.technical_task2.models.ModelWrapResult
import com.example.technical_task2.repository.RetrofitRepository

class MainViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repository by lazy { RetrofitRepository() }

    val liveDataModelResult: LiveData<ModelWrapResult<List<ModelResult>>> = repository.liveDataModelResult

    fun getListId() {
        repository.getListId()
    }
}