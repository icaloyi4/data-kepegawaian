package com.example.datapegawai.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pegawai")
class PegawaiEntity(var nama: String, var tempatLahir: String, var tanggalLahir: String, var alamat: String ? = "", var idPerusahaan: Int, var idJabatan: Int, @PrimaryKey(autoGenerate = true) var id: Int?)