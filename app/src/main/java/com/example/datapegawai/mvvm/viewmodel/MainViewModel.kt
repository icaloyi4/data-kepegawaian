package com.example.datapegawai.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PegawaiEntity
import com.example.datapegawai.database.entity.PegawaiModel
import com.example.datapegawai.mvvm.model.MainRepository
import com.example.datapegawai.utils.App
import kotlinx.coroutines.launch


class MainViewModel(val repo: MainRepository) : ViewModel() {
    val idPerusahaan = App.preff.getData(App.keyIdPerusahaan).toString().toInt()
    val kolom = "p.idJabatan"
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _pegawaiData = MutableLiveData<List<PegawaiModel>>()
    val perusahaanData: LiveData<List<PegawaiModel>>
        get() = _pegawaiData

    private val _jabatanData = MutableLiveData<List<JabatanEntity>>()
    val jabatanData: LiveData<List<JabatanEntity>>
        get() = _jabatanData

    init {

        getDataPegawaiRaw(idPerusahaan, kolom)
        getJabatann(idPerusahaan)
    }

    fun getDataPegawaiRaw(idPerusahaan: Int, kolom: String){
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            var queryRawPegawai = "Select p.*, j.namaJabatan as jabatan from pegawai p join jabatan j on p.idJabatan=j.id  where p.idPerusahaan=$idPerusahaan order by $kolom asc"

            _pegawaiData.postValue(repo.getDataPegawaiRaw(SimpleSQLiteQuery(queryRawPegawai)))
            _loadingState.postValue(LoadingState.LOADED)
        }
    }

    fun getJabatann(idPerusahaan: Int){
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            _jabatanData.postValue(repo.getJabatan(idPerusahaan))
            _loadingState.postValue(LoadingState.LOADED)
        }
    }

    fun insertPegawai(pegawaiEntity: PegawaiEntity){
        viewModelScope.launch {
            repo.insertPegawai(pegawaiEntity)
            getDataPegawaiRaw(idPerusahaan, kolom)
        }
    }

    fun deletePegawai(idPegawai: Int){
        viewModelScope.launch {
            repo.deletePegawai(idPerusahaan, idPegawai)
            getDataPegawaiRaw(idPerusahaan, kolom)
        }
    }

}