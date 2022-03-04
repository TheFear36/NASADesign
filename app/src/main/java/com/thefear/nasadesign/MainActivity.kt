package com.thefear.nasadesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.thefear.nasadesign.databinding.ActivityMainBinding
import com.thefear.nasadesign.ui.screens.MainFragment
import com.thefear.nasadesign.ui.screens.SettingsFragment
import com.thefear.nasadesign.ui.screens.SunSystemFragment


const val BaseTheme = 1
const val RedTheme = 2
const val BlueTheme = 3
const val GreenTheme = 4

class MainActivity : AppCompatActivity() {


    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
/*        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }*/
        setTheme(getRealStyle(getCurrentTheme()))
        setContentView(binding.root)
        initBottomNavigation()
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

    private fun initBottomNavigation() = with(binding) {
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.sunSystem -> {
                    openFragment(SunSystemFragment.newInstance())
                    true
                }
                R.id.pictureOfTheDay -> {
                    openFragment(MainFragment.newInstance())
                    true
                }
                R.id.settings -> {
                    openFragment(SettingsFragment.newInstance())
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.pictureOfTheDay
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

}