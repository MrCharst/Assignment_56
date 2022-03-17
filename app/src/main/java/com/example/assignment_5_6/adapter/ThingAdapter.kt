package com.example.assignment_5_6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_5_6.R
import com.example.assignment_5_6.model.Item

class ThingAdapter(private val thingsList: ArrayList<Item>) :
    RecyclerView.Adapter<ThingAdapter.ThingHolder>() {

    inner class ThingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Item) {
            val textView: TextView = itemView.findViewById(R.id.textViewName)
            val imgView: ImageView = itemView.findViewById(R.id.imageViewItem)
            textView.text = item.name
            imgView.setImageResource(item.imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ThingHolder(itemView)
    }

    override fun onBindViewHolder(holder: ThingHolder, position: Int) {
        holder.bindData(thingsList[position])
    }

    override fun getItemCount(): Int {
        return thingsList.size
    }

}

