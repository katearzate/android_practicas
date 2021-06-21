package com.example.plataformasge.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelSchedule: ViewModel(){
    private var _listSubjects = MutableLiveData<ArrayList<Subject>>()
    val listSubjects: LiveData<ArrayList<Subject>> get() = _listSubjects

    fun setList(list: ArrayList<Subject>){
        _listSubjects.value = list
    }
}