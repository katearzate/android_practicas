package com.example.plataformaescolarv2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plataformaescolarv2.getters.Materia

class MateriasViewModel : ViewModel() {
    private var _listaMaterias = MutableLiveData<ArrayList<Materia>>()
    val listaMaterias : LiveData<ArrayList<Materia>> get() = _listaMaterias  //solo se usa para obtener datos
    //no se pueden modificar

    fun setLista(list: ArrayList<Materia>){
        _listaMaterias.value = list
    }

}