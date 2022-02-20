package com.thefear.nasadesign.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.thefear.nasadesign.R
import com.thefear.nasadesign.databinding.MainFragmentBinding
import com.thefear.nasadesign.viewmodel.AppState
import com.thefear.nasadesign.viewmodel.MainViewModel
import hide
import show
import snack

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
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
                imageNASA.show()
            }
        }
    }

    private fun loadData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()
    }


    companion object {
        fun newInstance() = MainFragment()
    }

}