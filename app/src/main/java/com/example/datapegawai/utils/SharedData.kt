package com.example.datapegawai.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedData(context : Context) {
    val reader : SharedPreferences
    var editor: SharedPreferences.Editor
    var dataLogin : String = ""

    init {
        reader = PreferenceManager.getDefaultSharedPreferences(context)
        editor = reader.edit()
    }


    fun getData(key: String?): String? {
        dataLogin = reader!!.getString(key, "")!!
        return dataLogin
    }

    fun removeData(key: String?) {
        editor!!.remove(key).commit()
    }

    fun setDataString(key: String?, data: String?) {
        editor!!.putString(key, data).commit()
    }

    fun resetData() {
        editor!!.clear().commit()
    }
}