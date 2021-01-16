package com.example.datapegawai.mvvm.model

import com.example.datapegawai.database.DataPegawaiDao

class LandingRepository(private val dataPegawaiDao: DataPegawaiDao) {
    suspend fun getDataPerusahaan() = dataPegawaiDao.getCountPerusahaan()
}