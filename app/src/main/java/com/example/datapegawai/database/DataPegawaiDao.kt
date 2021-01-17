package com.example.datapegawai.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
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

    @Query("Select * from perusahaan where id=:idPerusahaan order by namaPerusahaan asc limit 1")
    fun getPerusahaanBaseId(idPerusahaan: Int) : List<PerusahaanEntity>

    @Query("Select p.*, j.namaJabatan as jabatan from pegawai p join jabatan j on p.idJabatan=j.id  where p.idPerusahaan=:idPerusahaan order by :kolom asc")
    fun getPegawai(idPerusahaan : Int, kolom : String) : List<PegawaiModel>

    @RawQuery
    fun getPegawaiRaw(sortQuery: SupportSQLiteQuery) : List<PegawaiModel>

    @Query("Select * from jabatan where idPerusahaan = :idPerusahaan")
    fun getJabatan(idPerusahaan : Int) : List<JabatanEntity>

    @Query("Select count(*) from pegawai where idPerusahaan = :idPerusahaan and idJabatan = :idJabatanEntity")
    fun getPegawaiJabatanCount(idPerusahaan : Int, idJabatanEntity: Int) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerusahaan(perusahaanEntity: PerusahaanEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJabatan(jabatanEntity: List<JabatanEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJabatanSingle(jabatanEntity: JabatanEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPegawai(pegawaiEntity: PegawaiEntity)

    @Query("delete from pegawai where idPerusahaan = :idPerusahaan and id = :idPegawai")
    fun deletePegawai(idPerusahaan: Int, idPegawai : Int)

    @Query("delete from jabatan where idPerusahaan = :idPerusahaan and id = :idJabatanEntity")
    fun deleteJabatan(idPerusahaan: Int, idJabatanEntity: Int)
}