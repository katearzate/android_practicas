package com.example.plataformasge.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelHomeFragments : ViewModel() {
    private var user = MutableLiveData<User>()
    val userEntered: LiveData<User> get() = user

    fun setUser(user1: User){
        user.value = user1
    }
}