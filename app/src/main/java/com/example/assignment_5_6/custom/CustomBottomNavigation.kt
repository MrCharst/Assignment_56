package com.example.assignment_5_6.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.assignment_5_6.R
import com.example.assignment_5_6.model.ItemBottom

class CustomBottomNavigation: ConstraintLayout {

    private lateinit var item1: ImageButton
    private lateinit var item2: ImageButton
    private lateinit var item3: ImageButton
    private lateinit var item4: ImageButton
    var listItem = arrayListOf<ItemBottom>()

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initView(context)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_bottom_navigation, this)
        item1 = view.findViewById(R.id.buttonCustom)
        item2 = view.findViewById(R.id.buttonLayout)
        item3 = view.findViewById(R.id.buttonText)
        item4 = view.findViewById(R.id.buttonDraw)

        listItem.apply {
            add(ItemBottom(item1, false))
            add(ItemBottom(item2, false))
            add(ItemBottom(item3, false))
            add(ItemBottom(item4, false))
        }
    }

    fun setChecked(id: Int) {
        listItem[id].isChecked = true
        for(i in listItem.indices) {
            if(i != id) {
                listItem[i].isChecked = false
            }
        }
    }

    fun setUnchecked(id: Int) {
        listItem[id].isChecked = false
    }
}