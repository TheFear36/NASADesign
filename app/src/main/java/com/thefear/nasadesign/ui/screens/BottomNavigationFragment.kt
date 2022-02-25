package com.thefear.nasadesign.ui.screens

import android.os.Bundle
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import com.thefear.nasadesign.MainActivity
import com.thefear.nasadesign.R
import com.thefear.nasadesign.databinding.FragmentBottomNavigationBinding
import com.thefear.nasadesign.databinding.FragmentMainBinding
import toast

class BottomNavigationFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNavigationMenu()
    }

    private fun loadNavigationMenu() = with(binding) {
        val navigationView: NavigationView = navigationView
        navigationView.inflateMenu(R.menu.menu_bottom_navigation)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.archive -> {
                    dismiss()
                    true
                }
                R.id.send -> {
                    dismiss()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}