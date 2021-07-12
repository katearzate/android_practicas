package com.example.projectubereats.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectubereats.models.User

class NotificationsViewModel : ViewModel() {

    private var user = MutableLiveData<User>()
    val userLog: LiveData<User> get() = user

    fun setUser(usr: User) {
        user.value = usr
    }

}