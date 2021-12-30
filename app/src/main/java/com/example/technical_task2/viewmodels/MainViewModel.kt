package com.example.technical_task2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.technical_task2.models.ModelResult
import com.example.technical_task2.models.ModelWrapResult
import com.example.technical_task2.repository.RetrofitRepository

class MainViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val liveDataModelResult: LiveData<ModelWrapResult<List<ModelResult>>> = repository.liveDataModelResult

    fun getListId() {
        repository.getListId()
    }
}