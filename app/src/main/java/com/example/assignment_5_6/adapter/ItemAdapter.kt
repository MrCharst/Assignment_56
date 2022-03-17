package com.example.assignment_5_6.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_5_6.R
import com.example.assignment_5_6.model.ItemRecycler

class ItemAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<ItemAdapter.ThingHolder>() {

    private var listItem = arrayListOf<ItemRecycler>()

    inner class ThingHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var tvName: TextView = itemView.findViewById(R.id.tvName)

        fun bindData(item: ItemRecycler) {
            tvName.text = item.name
            ObjectAnimator.ofFloat(itemView, View.SCALE_X, 0f, 1f).setDuration(((listItem.size-adapterPosition)*500).toLong()).start()
            ObjectAnimator.ofFloat(itemView, View.SCALE_Y, 0f, 1f).setDuration(((listItem.size-adapterPosition)*500).toLong()).start()
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setData(listItemUpdate: ArrayList<ItemRecycler>) {
        try {
            val diffResult = DiffUtil.calculateDiff(ItemDiffUtils(listItem, listItemUpdate))
            diffResult.dispatchUpdatesTo(this)
            listItem.clear()
            listItem.addAll(listItemUpdate)
        } catch (e: Exception) {
            listItem.clear()
            listItem.addAll(listItemUpdate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ThingHolder(itemView)
    }

    override fun onBindViewHolder(holder: ThingHolder, position: Int) {
        holder.bindData(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class ItemDiffUtils(private val oldData: ArrayList<ItemRecycler>, private val newData: ArrayList<ItemRecycler>): DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldData.getOrNull(oldItemPosition)
            val newItem = newData.getOrNull(newItemPosition)

            if(oldItem != null && newItem != null) {
                return oldItem.name == newItem.name
            }
            return false
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldData.getOrNull(oldItemPosition)
            val newItem = newData.getOrNull(newItemPosition)

            return oldItem == newItem
        }


    }

}

