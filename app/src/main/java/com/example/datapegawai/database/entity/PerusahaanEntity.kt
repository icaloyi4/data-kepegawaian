package com.example.datapegawai.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "perusahaan")
class PerusahaanEntity(
    var namaPerusahaan : String,
    @PrimaryKey(autoGenerate = true) var id : Int ? = null
) : Parcelable {
    override fun toString(): String {
        return namaPerusahaan
    }
}