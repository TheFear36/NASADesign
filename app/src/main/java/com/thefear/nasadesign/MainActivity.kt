package com.thefear.nasadesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.thefear.nasadesign.ui.screens.MainFragment


const val BaseTheme = 1
const val RedTheme = 2
const val BlueTheme = 3
const val GreenTheme = 4

class MainActivity : AppCompatActivity() {


    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        setTheme(getRealStyle(getCurrentTheme()))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        setContentView(R.layout.activity_main)
    }
    private fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }
    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            BaseTheme -> R.style.BaseTheme
            RedTheme -> R.style.RedTheme
            BlueTheme -> R.style.BlueTheme
            GreenTheme -> R.style.GreenTheme
            else -> 0
        }
    }

}