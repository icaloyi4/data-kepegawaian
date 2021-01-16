package com.example.datapegawai.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PerusahaanEntity

@Dao
interface DataPegawaiDao {
    @Query("Select count(*) from perusahaan")
    fun getCountPerusahaan() : Int

    @Query("Select * from perusahaan order by namaPerusahaan")
    fun getPerusahaan() : List<PerusahaanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerusahaan(perusahaanEntity: PerusahaanEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJabatan(jabatanEntity: List<JabatanEntity>)
}