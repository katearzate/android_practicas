package com.example.introfragmentos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FragActViewModel {
    private var editActivity = MutableLiveData<String>("")
    val editMensajeAct: LiveData<String> get() = editActivity

    fun setMessage(texto: String) {
        editActivity.value = texto
    }
}