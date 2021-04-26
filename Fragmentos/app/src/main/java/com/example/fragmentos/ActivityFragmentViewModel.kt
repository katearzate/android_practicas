package com.example.fragmentos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityFragmentViewModel : ViewModel() {
    private var editMensaje = MutableLiveData<String>("")
    val editMensajeIngresado: LiveData<String> get() = editMensaje

    fun setMensaje(texto: String){
        editMensaje.value = texto
    }
}