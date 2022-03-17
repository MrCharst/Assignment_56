package com.example.assignment_5_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_5_6.ui.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentMain, MainFragment())
            .commitAllowingStateLoss()

    }
}
