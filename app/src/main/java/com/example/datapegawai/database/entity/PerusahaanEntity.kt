package com.example.datapegawai.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perusahaan")
class PerusahaanEntity(
    var namaPerusahaan : String,
    @PrimaryKey(autoGenerate = true) var id : Int
)