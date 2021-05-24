package com.example.plataformaescolarv2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plataformaescolarv2.getters.Materia

class MateriasViewModel : ViewModel() {
    private var _listaMaterias = MutableLiveData<List<Materia>>()
    val listaMaterias : LiveData<List<Materia>> get() = _listaMaterias  //solo se usa para obtener datos
    //no se pueden modificar

    fun setLista(list: List<Materia>){
        _listaMaterias.value = list
    }

}