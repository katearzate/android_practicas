package com.example.projectubereats.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Tu te encuentras aquí..."
    }
    val text: LiveData<String> = _text

    private val lat = MutableLiveData<Double>()
    private var lng = MutableLiveData<Double>()

    fun getLat(): LiveData<Double?>? {
        return lat
    }

    fun setLat(latitude: Double?) {
        latitude?.let {
            lat.value = it
        }
    }

    fun getLng(): LiveData<Double?>? {
        return lng
    }

    fun setLng(longitude: Double?) {
        longitude?.let {
            lng.value = it
        }
    }

}