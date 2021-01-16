package com.example.datapegawai.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PegawaiEntity
import com.example.datapegawai.database.entity.PerusahaanEntity

@Database(entities = [JabatanEntity::class, PerusahaanEntity::class, PegawaiEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataPegawaiDao(): DataPegawaiDao
}