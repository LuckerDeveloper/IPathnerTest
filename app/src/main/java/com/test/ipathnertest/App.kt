package com.test.ipathnertest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class App: Application() {

    private val APP_PREFERENCES = "APP_PREFERENCES"
    private val SESSION_KEY="SESSION_KEY"
    lateinit var sharedPreferences : SharedPreferences

    companion object{
        lateinit var networkManager : NetworkManager
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        networkManager = NetworkManager()
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val session = sharedPreferences.getString(SESSION_KEY, null)
        if (!session.isNullOrBlank()){
            networkManager.session=session
        }
    }

    fun saveSession(session : String){
        sharedPreferences
            .edit()
            .putString(SESSION_KEY, session)
            .apply()
    }
}