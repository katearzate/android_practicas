package com.example.projectubereats.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectubereats.models.User

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Datos"
    }
    val text: LiveData<String> = _text

    private var user = MutableLiveData<User>()

    fun setUser(usr: User?) {
        usr?.let {
            user.value = it
        }
    }

    fun getUser(): LiveData<User?>? {
        return user
    }

}