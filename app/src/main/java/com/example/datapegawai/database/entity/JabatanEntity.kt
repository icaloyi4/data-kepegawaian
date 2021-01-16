package com.example.datapegawai.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jabatan")
class JabatanEntity(
    var namaJabatan : String,
    var idPerusahaan : Int ? = null,
    @PrimaryKey(autoGenerate = true) var id : Int ? = null)