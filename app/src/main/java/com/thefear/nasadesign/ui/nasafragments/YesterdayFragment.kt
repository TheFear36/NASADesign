package com.thefear.nasadesign.ui.nasafragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.thefear.nasadesign.MainActivity
import com.thefear.nasadesign.R
import com.thefear.nasadesign.databinding.FragmentYesterdayBinding
import com.thefear.nasadesign.viewmodel.AppState
import com.thefear.nasadesign.viewmodel.MainViewModel
import hide
import show
import snack

class YesterdayFragment : Fragment() {
    private var _binding: FragmentYesterdayBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYesterdayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.sendServerRequestPOY()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Error -> {
                container.snack(R.string.error, R.string.reload, loadData())
            }
            AppState.Loading -> {
                image.hide()
                progressBar.show()
            }
            is AppState.Success -> {
                progressBar.hide()
                image.load(appState.dto.hdurl)
                image.show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}