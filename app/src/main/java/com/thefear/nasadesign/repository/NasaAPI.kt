package com.thefear.nasadesign.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {

    @GET("planetary/apod")
    fun getPictureOfTheToday(@Query("apy_key") apiKey: String): Call<DTONasa>
}