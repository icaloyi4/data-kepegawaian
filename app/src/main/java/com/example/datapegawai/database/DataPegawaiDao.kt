package com.example.datapegawai.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PegawaiEntity
import com.example.datapegawai.database.entity.PegawaiModel
import com.example.datapegawai.database.entity.PerusahaanEntity

@Dao
interface DataPegawaiDao {
    @Query("Select count(*) from perusahaan")
    fun getCountPerusahaan() : Int

    @Query("Select * from perusahaan order by namaPerusahaan asc")
    fun getPerusahaan() : List<PerusahaanEntity>

    @Query("Select p.*, j.namaJabatan as jabatan from pegawai p join jabatan j on p.idJabatan=j.id  where p.idPerusahaan=:idPerusahaan order by :kolom asc")
    fun getPegawai(idPerusahaan : Int, kolom : String) : List<PegawaiModel>

    @Query("Select * from jabatan where idPerusahaan = :idPerusahaan")
    fun getJabatan(idPerusahaan : Int) : List<JabatanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerusahaan(perusahaanEntity: PerusahaanEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJabatan(jabatanEntity: List<JabatanEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPegawai(pegawaiEntity: PegawaiEntity)
}