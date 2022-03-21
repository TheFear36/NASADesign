package com.thefear.nasadesign.repository

import com.google.gson.GsonBuilder
import com.thefear.nasadesign.BuildConfig
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepositoryImpl {
    private val baseUrl = "https://api.nasa.gov/"
    private val apiKey = BuildConfig.NASA_API_KEY

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(NasaAPI::class.java)
    }

    fun getPictureOfTheToday(podCallback: Callback<DTONasa>) {
        api.getPictureOfTheToday(apiKey).enqueue(podCallback)
    }

    fun getPictureOfTheDay(date: String, podCallback: Callback<DTONasa>) {
        api.getPictureOfTheDay(apiKey, date).enqueue(podCallback)
    }
}