package com.example.datapegawai.database.entity

class PegawaiModel(
    var nama : String,
    var tempatLahir : String,
    var tanggalLahir : String,
    var alamat : String ? = "",
    var idPerusahaan : Int,
    var idJabatan : Int,
    var id : Int,
    var jabatan : String,
) {
}