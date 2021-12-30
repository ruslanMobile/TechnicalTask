package com.example.technical_task2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.technical_task2.viewmodels.FactoryViewModel
import com.example.technical_task2.R
import com.example.technical_task2.adapters.RecyclerAdapter
import com.example.technical_task2.fragments.ProfileFragment
import com.example.technical_task2.models.ModelResult
import com.example.technical_task2.models.ModelWrapResult
import com.example.technical_task2.models.ModelWrapResult.*
import com.example.technical_task2.repository.RetrofitRepository
import com.example.technical_task2.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(),RecyclerAdapter.ClickShowProfileListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragment: FragmentContainerView
    private lateinit var buttonGet: Button
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: RecyclerAdapter
    private lateinit var textError: TextView
    private lateinit var progressBar: ProgressBar
    private var listPeople: List<ModelResult> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.recyclerView)
        fragment = findViewById(R.id.fragmentProfile)
        textError = findViewById(R.id.textError)
        progressBar = findViewById(R.id.progressBar)
        buttonGet = findViewById(R.id.buttonGetData)
        //Get list
        buttonGet.setOnClickListener {
            viewModel.getListId()
            progressBar.visibility = View.VISIBLE
        }

        adapter = RecyclerAdapter(listOf(),this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, FactoryViewModel(RetrofitRepository())).get(MainViewModel::class.java)
        //Checks whether the Api request is successful and show
        viewModel.liveDataModelResult.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        listPeople = it1
                        adapter.changeList(it1)
                        textError.visibility = View.GONE
                        progressBar.visibility = View.GONE
                    }

                }
                Status.ERROR -> {
                    textError.text = "Error: " + it.message + ". Please try again"
                    textError.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onClickShowProfile(position: Int) {
        var bundle = Bundle()
        bundle.putSerializable("person",listPeople[position])
        supportFragmentManager.beginTransaction().replace(R.id.fragmentProfile,ProfileFragment::class.java,bundle).commit()
    }
}