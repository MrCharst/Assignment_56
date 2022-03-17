package com.example.assignment_5_6.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment_5_6.model.Item
import com.example.assignment_5_6.model.ItemModel

class MainViewModel : ViewModel() {

    var isVisibility: ObservableField<Boolean> = ObservableField()
    var itemModel = ItemModel()
    var check: MutableLiveData<String> = MutableLiveData()

    fun changeData(): ArrayList<Item> {
        var arr = ArrayList<Item>()
        when (check.value) {
            "Custom" -> {
                arr = itemModel.arrCustom()
            }
            "Layout" -> {
                arr = itemModel.arrLayout()
            }
            "Text" -> {
                arr = itemModel.arrText()
            }
            "Draw" -> {
                arr = itemModel.arrDraw()
            }

        }
        return arr
    }

    fun clickBtnCustom() {
        isVisibility.set(true)
        check.value = "Custom"

    }

    fun clickBtnHome() {
        isVisibility.set(false)
    }

    fun clickBtnLayout() {
        isVisibility.set(true)
        check.value = "Layout"
    }

    fun clickBtnText() {
        isVisibility.set(true)
        check.value = "Text"
    }

    fun clickBtnDraw() {
        isVisibility.set(true)
        check.value = "Draw"
    }
}