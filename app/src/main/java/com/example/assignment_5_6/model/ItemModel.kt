package com.example.assignment_5_6.model

import com.example.assignment_5_6.R

class ItemModel() {

    fun arrCustom(): ArrayList<Item> {
        val arrItem = ArrayList<Item>()
        arrItem.add(Item(1, R.drawable.ic_draw, "One"))
        return arrItem
    }

    fun arrLayout(): ArrayList<Item> {
        val arrItem = ArrayList<Item>()
        arrItem.add(Item(1, R.drawable.ic_draw, "One"))
        arrItem.add(Item(2, R.drawable.ic_layout, "Two"))
        return arrItem
    }

    fun arrText(): ArrayList<Item> {
        val arrItem = ArrayList<Item>()
        arrItem.add(Item(1, R.drawable.ic_draw, "One"))
        arrItem.add(Item(2, R.drawable.ic_layout, "Two"))
        arrItem.add(Item(3, R.drawable.ic_custom, "Three"))
        return arrItem
    }

    fun arrDraw(): ArrayList<Item> {
        val arrItem = ArrayList<Item>()
        arrItem.add(Item(1, R.drawable.ic_draw, "One"))
        arrItem.add(Item(2, R.drawable.ic_layout, "Two"))
        arrItem.add(Item(3, R.drawable.ic_custom, "Three"))
        arrItem.add(Item(4, R.drawable.ic_text, "Four"))
        arrItem.add(Item(5, R.drawable.ic_home, "Five"))
        return arrItem
    }
}