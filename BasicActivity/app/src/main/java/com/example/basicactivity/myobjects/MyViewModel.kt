package com.example.basicactivity.myobjects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val pelicula = MutableLiveData<Movie>()
    private val compra = MutableLiveData<Compra>()

    fun setMovie(name: Movie?) {
        pelicula.value = name
    }

    fun getMovie(): LiveData<Movie?>? {
        return pelicula
    }

    fun setCompra(buy: Compra?) {
        compra.value = buy
    }

    fun getCompra(): LiveData<Compra?>? {
        return compra
    }

}