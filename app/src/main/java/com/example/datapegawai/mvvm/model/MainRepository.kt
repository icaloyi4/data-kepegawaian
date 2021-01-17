package com.example.datapegawai.mvvm.model

import androidx.sqlite.db.SupportSQLiteQuery
import com.example.datapegawai.database.DataPegawaiDao
import com.example.datapegawai.database.entity.PegawaiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val dataPegawaiDao: DataPegawaiDao) {
    suspend fun getDataPegawai(idPerusahaan : Int, kolom : String) = dataPegawaiDao.getPegawai(idPerusahaan, kolom)
    suspend fun getDataPegawaiRaw(sortQuery: SupportSQLiteQuery) = dataPegawaiDao.getPegawaiRaw(sortQuery)
    suspend fun getJabatan(idPerusahaan: Int) = dataPegawaiDao.getJabatan(idPerusahaan)
    suspend fun insertPegawai(pegawaiEntity: PegawaiEntity) = withContext(Dispatchers.IO) { dataPegawaiDao.insertPegawai(pegawaiEntity)}
    suspend fun deletePegawai(idPerusahaan : Int, idPegawai : Int) = withContext(Dispatchers.IO) { dataPegawaiDao.deletePegawai(idPerusahaan, idPegawai)}
}