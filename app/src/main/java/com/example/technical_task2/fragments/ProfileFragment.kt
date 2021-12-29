package com.example.technical_task2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.technical_task2.R
import com.example.technical_task2.models.ModelResult
import java.io.Serializable

class ProfileFragment : Fragment() {

    lateinit var textNameAndSurname:TextView
    lateinit var textAgeEdit:TextView
    lateinit var textGenderEdit:TextView
    lateinit var textCountryEdit:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var modelResult = arguments?.getSerializable("person") as ModelResult

        textNameAndSurname = view.findViewById(R.id.textNameAndSurname)
        textAgeEdit = view.findViewById(R.id.textAgeEdit)
        textGenderEdit = view.findViewById(R.id.textGenderEdit)
        textCountryEdit = view.findViewById(R.id.textCountryEdit)

        textNameAndSurname.text = modelResult.data.firstName + " " + modelResult.data.lastName
        textAgeEdit.text = modelResult.data.age.toString()
        textGenderEdit.text = modelResult.data.gender
        textCountryEdit.text = modelResult.data.country
    }
}