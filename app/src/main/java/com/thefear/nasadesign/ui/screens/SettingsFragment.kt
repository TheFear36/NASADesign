package com.thefear.nasadesign.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.thefear.nasadesign.*
import com.thefear.nasadesign.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity =
            requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        yellowTheme.setOnClickListener { onRadioButtonClick(yellowTheme) }
        redTheme.setOnClickListener { onRadioButtonClick(redTheme) }
        blueTheme.setOnClickListener { onRadioButtonClick(blueTheme) }
        greenTheme.setOnClickListener { onRadioButtonClick(greenTheme) }
        when (getCurrentTheme()) {
            1 -> radioGroup.check(R.id.yellowTheme)
            2 -> radioGroup.check(R.id.redTheme)
            3 -> radioGroup.check(R.id.blueTheme)
            4 -> radioGroup.check(R.id.greenTheme)
        }
    }

    private fun onRadioButtonClick(v: View) {
        when (v.id) {
            R.id.yellowTheme -> {
                setCurrentTheme(BaseTheme)
                updateTheme()
            }
            R.id.redTheme -> {
                setCurrentTheme(RedTheme)
                updateTheme()
            }
            R.id.blueTheme -> {
                setCurrentTheme(BlueTheme)
                updateTheme()
            }
            R.id.greenTheme -> {
                setCurrentTheme(GreenTheme)
                updateTheme()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun updateTheme() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
        parentActivity.recreate()
    }

    private fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences =
            requireActivity().getSharedPreferences(KEY_SP, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }
    private fun getCurrentTheme(): Int {
        val sharedPreferences =
            requireActivity().getSharedPreferences(KEY_SP, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }

}