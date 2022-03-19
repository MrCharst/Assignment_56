package com.example.assignment_5_6.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.assignment_5_6.model.adapter.ItemRecyclerAdapter
import com.example.assignment_5_6.custom.CustomArcLayoutManager
import com.example.assignment_5_6.databinding.FragmentMainBinding
import com.example.assignment_5_6.model.ItemRecycler
import kotlin.properties.Delegates


class MainFragment : Fragment(), ItemRecyclerAdapter.OnItemClickListener {
    private lateinit var binding: FragmentMainBinding

    private lateinit var listItem: ArrayList<ItemRecycler>
    private val adapter = ItemRecyclerAdapter(this)

    private var itemBottomId by Delegates.notNull<Int>()
    private var isRecyclerViewShowing: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        binding.constraintBottom.listItem[0].iconBottom.setOnClickListener {
            listItem = arrayListOf(ItemRecycler( "Item 1"), ItemRecycler( "Item 2"), ItemRecycler( "Item 3"))
            setLayout(listItem, 0)
        }

        binding.constraintBottom.listItem[1].iconBottom.setOnClickListener {
            listItem = arrayListOf(ItemRecycler( "Item 1"), ItemRecycler( "Item 2"))
            setLayout(listItem, 1)
        }

        binding.constraintBottom.listItem[2].iconBottom.setOnClickListener {
            listItem = arrayListOf(ItemRecycler( "Item 1"))
            setLayout(listItem, 2)
        }

        binding.constraintBottom.listItem[3].iconBottom.setOnClickListener {
            listItem = arrayListOf(ItemRecycler( "Item 1"), ItemRecycler( "Item 2"), ItemRecycler( "Item 3"), ItemRecycler( "Item 4"), ItemRecycler( "Item 5"), ItemRecycler("Item 6"))
            setLayout(listItem, 3)
        }
    }

    private fun setLayout(listItem: ArrayList<ItemRecycler>, idItem: Int) {
        binding.constraintBottom.setChecked(idItem)
        if(!isRecyclerViewShowing) {
            binding.recyclerView.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(binding.recyclerView, View.TRANSLATION_Y, 300f, 0f).setDuration(1000).start()
            setValue(listItem)
            isRecyclerViewShowing = true
        } else if(binding.recyclerView.visibility == View.VISIBLE && itemBottomId == idItem) {
            ObjectAnimator.ofFloat(binding.recyclerView, View.TRANSLATION_Y, 0f, 300f).setDuration(1000).start()
            isRecyclerViewShowing = false
            binding.constraintBottom.setUnchecked(idItem)
        } else if(isRecyclerViewShowing && itemBottomId != idItem) {
            setValue(listItem)
        }
        itemBottomId = idItem
    }

    private fun setValue(listItemUpdate: ArrayList<ItemRecycler>) {
        adapter.setData(listItemUpdate)
        binding.recyclerView.layoutManager = CustomArcLayoutManager(requireContext(), listItemUpdate.size > 4)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, listItem[position].name, Toast.LENGTH_SHORT).show()
    }
}

