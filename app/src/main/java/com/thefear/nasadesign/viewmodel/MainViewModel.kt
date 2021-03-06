package com.thefear.nasadesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thefear.nasadesign.BuildConfig
import com.thefear.nasadesign.repository.DTONasa
import com.thefear.nasadesign.repository.RemoteRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RemoteRepositoryImpl = RemoteRepositoryImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun sendServerRequest() {
        liveData.value = AppState.Loading
        repository.getPictureOfTheToday(PODCallback)
    }

    fun sendServerRequestPOY() {
        liveData.value = AppState.Loading
        repository.getPictureOfTheDay(takeDate(-1), PODCallback)
    }
    fun sendServerRequestPOBY() {
        liveData.value = AppState.Loading
        repository.getPictureOfTheDay(takeDate(-2), PODCallback)
    }
    fun sendServerRequestPOTDA() {
        liveData.value = AppState.Loading
        repository.getPictureOfTheDay(takeDate(-3), PODCallback)
    }

    private val PODCallback = object : Callback<DTONasa> {
        override fun onResponse(call: Call<DTONasa>, response: Response<DTONasa>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.value = AppState.Success(it)
                }
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveData.value = AppState.Error(Throwable("Unidentified error"))

                }
            }
        }

        override fun onFailure(call: Call<DTONasa>, t: Throwable) {
            liveData.value = AppState.Error(t)

        }
    }

    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }

}