package com.thefear.nasadesign.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.thefear.nasadesign.R
import com.thefear.nasadesign.databinding.FragmentMainBinding
import com.thefear.nasadesign.viewmodel.AppState
import com.thefear.nasadesign.viewmodel.MainViewModel
import hide
import show
import snack

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        loadClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Error -> {
                main.snack(R.string.error, R.string.reload, loadData())
            }
            AppState.Loading -> {
                imageNASA.hide()
                progressBar.show()
            }
            is AppState.Success -> {
                progressBar.hide()
                imageNASA.load(appState.dto.hdurl)
                tvTitleDTO.text = appState.dto.title
                tvDescriptionDTO.text = appState.dto.explanation
                imageNASA.show()
            }
        }
    }

    private fun clickOnEndIcon() = with(binding) {
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://material.io/search.html?q=${inputEditText.text.toString()}")
        })
    }

    private fun clickOnNASAIcon() = with(binding) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    private fun loadData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.sendServerRequest()
    }

    private fun loadClickListeners() = with(binding) {
        imageNASA.setOnClickListener {
            clickOnNASAIcon()
        }
        searchWiki.setEndIconOnClickListener {
            clickOnEndIcon()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}