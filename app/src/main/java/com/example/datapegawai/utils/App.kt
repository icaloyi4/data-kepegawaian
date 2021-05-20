package com.example.datapegawai.utils

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    companion object {
        lateinit var preff : SharedData
        var idPerusahaan = ""
        var keyIdPerusahaan = "idPerusahaan"

        var namaPerusahaan = ""
        var keyNamaPerusahaan = "namaPerusahaan"
    }


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule, databaseModule))
        }

        preff = SharedData(applicationContext)
        if (preff.getData(keyIdPerusahaan).toString().isNotEmpty()) idPerusahaan = preff.getData(keyIdPerusahaan).toString()
        if (preff.getData(keyNamaPerusahaan).toString().isNotEmpty()) namaPerusahaan = preff.getData(keyNamaPerusahaan).toString()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}