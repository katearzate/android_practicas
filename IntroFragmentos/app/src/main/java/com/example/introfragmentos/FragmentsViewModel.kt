package com.example.introfragmentos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FragmentsViewModel {
    private val editActivity = MutableLiveData<String>("")
    val editTextActivity: LiveData<String> get() = editActivity

    fun setMessage(texto: String) {
        editActivity.value = texto
    }
}