package com.thefear.nasadesign.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepositoryImpl {
    private val baseUrl = "https://api.nasa.gov/"
    fun getRetrofitImpl(): NasaAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return  retrofit.create(NasaAPI::class.java)
    }
}