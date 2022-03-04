package com.thefear.nasadesign.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.thefear.nasadesign.databinding.FragmentSunSystemBinding
import com.thefear.nasadesign.ui.nasafragments.ViewPagerAdapter

class SunSystemFragment : Fragment() {
    private var _binding: FragmentSunSystemBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSunSystemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, requireActivity().lifecycle)
        TabLayoutMediator(tabLayout, viewPager) { _, _ ->

        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = SunSystemFragment()
    }


}