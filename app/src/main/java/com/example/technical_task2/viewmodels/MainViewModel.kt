package com.example.technical_task2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technical_task2.models.ModelListId
import com.example.technical_task2.repository.RetrofitRepository

class MainViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repository by lazy { RetrofitRepository() }

    val liveDataListId: LiveData<ModelListId> = repository.liveDataListId

    fun getListId() {
        repository.getListId()
    }
}