package com.example.datapegawai.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PegawaiEntity
import com.example.datapegawai.database.entity.PegawaiModel
import com.example.datapegawai.mvvm.model.MainRepository
import com.example.datapegawai.utils.App
import kotlinx.coroutines.launch

class MainViewModel(val repo: MainRepository) : ViewModel() {
    val idPerusahaan = App.preff.getData(App.keyIdPerusahaan).toString().toInt()
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
        getDataPegawai(idPerusahaan, "p.nama")
        getJabatann(idPerusahaan)
    }

    fun getDataPegawai(idPerusahaan : Int, kolom : String){
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            _pegawaiData.postValue(repo.getDataPegawai(idPerusahaan, kolom))
            _loadingState.postValue(LoadingState.LOADED)
        }
    }

    fun getJabatann(idPerusahaan : Int){
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            _jabatanData.postValue(repo.getJabatan(idPerusahaan))
            _loadingState.postValue(LoadingState.LOADED)
        }
    }

    fun insertPegawai(pegawaiEntity: PegawaiEntity){
        viewModelScope.launch {
            repo.insertPegawai(pegawaiEntity)
            getDataPegawai(idPerusahaan, "p.nama")
        }
    }

}