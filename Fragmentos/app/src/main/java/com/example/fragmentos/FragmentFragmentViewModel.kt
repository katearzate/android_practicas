package com.example.fragmentos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentFragmentViewModel : ViewModel() {
    val textoFrag = MutableLiveData<String>()
}