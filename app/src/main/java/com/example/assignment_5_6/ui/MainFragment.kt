package com.example.assignment_5_6.ui

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_5_6.adapter.ArcLayoutManager
import com.example.assignment_5_6.adapter.CustomLayoutManager
import com.example.assignment_5_6.adapter.ThingAdapter
import com.example.assignment_5_6.databinding.FragmentMainBinding
import com.example.assignment_5_6.viewmodel.MainViewModel


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private var mainViewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainViewModel = mainViewModel

        val recyclerView: RecyclerView = binding.recyclerView

        mainViewModel.check.observe(viewLifecycleOwner, Observer<String> { check ->
            when (check) {
                "Custom" -> {
                    recyclerView.adapter = ThingAdapter(mainViewModel.changeData())
                }
                "Layout" -> {
                    recyclerView.adapter = ThingAdapter(mainViewModel.changeData())
                }
                "Draw" -> {
                    recyclerView.adapter = ThingAdapter(mainViewModel.changeData())
                }
                "Text" -> {
                    recyclerView.adapter = ThingAdapter(mainViewModel.changeData())
                }
            }
        })

        val size = Point()
        activity?.windowManager?.defaultDisplay?.getSize(size)
        val screenWidth = size.x
        recyclerView.layoutParams.height = screenWidth / 2
        recyclerView.layoutManager = CustomLayoutManager(resources, screenWidth)
//            recyclerView.layoutManager =ArcLayoutManager(requireContext(),5)


    }




}

