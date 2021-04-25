package com.example.introfragmentos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ActivityViewModel {
    private val editActivity = MutableLiveData<CharSequence>()
    val editTextActivity: LiveData<CharSequence> get() = editActivity

    fun setText(texto: CharSequence) {
        editActivity.value = texto
    }
}