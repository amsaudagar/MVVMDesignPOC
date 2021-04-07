package com.android.mvvmdesignpoc.core.platform

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * This class allows android shared preferences interaction in one line. There
 * is little to no processing when getting or setting information. It is a
 * singleton, so calling getInstance() is needed.
 *
 */
class AppSharedPreferences
/**
 * private constructor
 * @param context any android Context to initialize instance
 */
private constructor(private val mContext: Context) {


    init {
        sSharedPreferences = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }


    @SuppressLint("ApplySharedPref")
    fun setValue(key_name: String, key_value: String) {
        val sEditor = sSharedPreferences?.edit()
        sEditor?.putString(key_name, key_value)
        sEditor?.commit()
    }

    @SuppressLint("ApplySharedPref")
    fun setValue(key_name: String, key_value: Boolean) {
        val sEditor = sSharedPreferences?.edit()
        sEditor?.putBoolean(key_name, key_value)
        sEditor?.commit()
    }

    fun getValue(key_name: String, default_value: String): String? {
        return sSharedPreferences?.getString(key_name, default_value)
    }

    fun getValue(key_name: String, default_value: Boolean): Boolean? {
        return sSharedPreferences?.getBoolean(key_name, default_value)
    }

    fun clearAllData() {
        sSharedPreferences!!.edit().clear().apply()
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: AppSharedPreferences? = null
        private val PREFS_NAME = "ApplicationPreferencesFile"
        var sSharedPreferences: SharedPreferences? = null

        /**
         * method to retrieve instance
         * @param context any android Context, the first time is stored internally to instantiate singleton.
         * @return the instance
         */
        fun getInstance(context: Context): AppSharedPreferences {
            if (instance == null) {
                instance = AppSharedPreferences(context)
            }
            return instance as AppSharedPreferences
        }
    }
}