package com.example.assignment_5_6.model

import android.graphics.Color
import android.widget.ImageButton
import com.example.assignment_5_6.R

data class ItemBottom(val iconBottom: ImageButton, var _checked: Boolean) {
    var isChecked: Boolean = this._checked
        set(value) {
            if(value) {
                this.iconBottom.setBackgroundResource(R.drawable.backgroud_item_bottom)
            } else {
                this.iconBottom.setBackgroundColor(Color.WHITE)
            }
            field = value
        }
}