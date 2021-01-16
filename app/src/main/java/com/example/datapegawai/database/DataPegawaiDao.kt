package com.example.datapegawai.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DataPegawaiDao {
    @Query("Select count(*) from perusahaan")
    fun getCountPerusahaan() : Int
}