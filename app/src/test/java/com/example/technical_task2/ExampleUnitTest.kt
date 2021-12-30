package com.example.technical_task2

import com.example.technical_task2.repository.RetrofitRepository
import com.example.technical_task2.viewmodels.MainViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    lateinit var repository: RetrofitRepository
    lateinit var viewModel: MainViewModel

    @Before
    fun init() {
        repository = RetrofitRepository()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun liveDataRepositoryShouldBeNotNull() {
        assertNotNull(repository.liveDataModelResult)
    }
    @Test
    fun liveDataViewModelShouldBeNotNull() {
        assertNotNull(viewModel.liveDataModelResult)
    }
}