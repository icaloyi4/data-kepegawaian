package com.example.datapegawai.mvvm.model

import com.example.datapegawai.database.DataPegawaiDao
import com.example.datapegawai.database.entity.PegawaiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val dataPegawaiDao: DataPegawaiDao) {
    suspend fun getDataPegawai(idPerusahaan : Int, kolom : String) = dataPegawaiDao.getPegawai(idPerusahaan, kolom)
    suspend fun getJabatan(idPerusahaan: Int) = dataPegawaiDao.getJabatan(idPerusahaan)
    suspend fun insertPegawai(pegawaiEntity: PegawaiEntity) = withContext(Dispatchers.IO) { dataPegawaiDao.insertPegawai(pegawaiEntity)}
}