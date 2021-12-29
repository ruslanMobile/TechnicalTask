package com.example.technical_task2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.technical_task2.R
import com.example.technical_task2.models.ModelResult

class RecyclerAdapter(
    private var data: List<ModelResult>,
    private var clickShowProfileListener: ClickShowProfileListener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    fun changeList(newList: List<ModelResult>) {
        data = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view, clickShowProfileListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = data[position].data.firstName
    }

    override fun getItemCount() = data.size

    class ViewHolder(view: View, clickShowProfileListener: ClickShowProfileListener) :
        RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val clickShowProfileListener_: ClickShowProfileListener

        init {
            textViewName = view.findViewById(R.id.textViewName)
            clickShowProfileListener_ = clickShowProfileListener
            view.setOnClickListener{
                clickShowProfileListener_.onClickShowProfile(absoluteAdapterPosition)
            }
        }
    }

    interface ClickShowProfileListener {
        fun onClickShowProfile(position: Int)
    }
}