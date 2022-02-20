package com.thefear.nasadesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thefear.nasadesign.R
import com.thefear.nasadesign.ui.screens.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}