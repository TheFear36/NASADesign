package com.thefear.nasadesign.viewmodel

import com.thefear.nasadesign.repository.DTONasa

sealed class AppState {
    data class Success(val dto: DTONasa) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
