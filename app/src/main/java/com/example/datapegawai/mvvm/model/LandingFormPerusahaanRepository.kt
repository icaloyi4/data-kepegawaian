package com.example.datapegawai.mvvm.model

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.datapegawai.R
import com.example.datapegawai.adapter.JabatanAdapter
import com.example.datapegawai.database.DataPegawaiDao
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.utils.Utils
import kotlinx.android.synthetic.main.fragment_f_perusahaan.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LandingFormPerusahaanRepository(private val dataPegawaiDao: DataPegawaiDao) {
    suspend fun insertPerusahaan(perusahaanEntity: PerusahaanEntity) = withContext(Dispatchers.IO) { dataPegawaiDao.insertPerusahaan(perusahaanEntity)}
    suspend fun insertJabatan(jabatanEntity: List<JabatanEntity>) = withContext(Dispatchers.IO) { dataPegawaiDao.insertJabatan(jabatanEntity)}
}