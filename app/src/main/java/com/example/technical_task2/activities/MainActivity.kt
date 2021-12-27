package com.example.technical_task2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.technical_task2.R
import com.example.technical_task2.repository.RetrofitRepository
import com.example.technical_task2.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var fragment:Fragment
    private lateinit var buttonGet:Button
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.liveDataListId.observe(this,{
            Log.d("MyLog",it.toString())
        })

        /*recyclerView = findViewById(R.id.recyclerView)
        fragment = findViewById(R.id.fragmentProfile)*/
        buttonGet = findViewById(R.id.buttonGetData)
        buttonGet.setOnClickListener { id->  viewModel.getListId() }
    }
}