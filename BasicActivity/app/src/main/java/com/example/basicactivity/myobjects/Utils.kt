package com.example.basicactivity.myobjects

import android.content.Context
import android.widget.Toast

class Utils {

    companion object {

        fun String.toast(c: Context) {
            Toast.makeText(c, this, Toast.LENGTH_LONG).show()
        }

    }

}