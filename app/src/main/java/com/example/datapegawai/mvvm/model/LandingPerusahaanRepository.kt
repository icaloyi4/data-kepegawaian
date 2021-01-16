package com.example.datapegawai.mvvm.model

import com.example.datapegawai.database.DataPegawaiDao

class LandingPerusahaanRepository(private val dataPegawaiDao: DataPegawaiDao) {
    suspend fun getDataPerusahaan() = dataPegawaiDao.getPerusahaan()
}