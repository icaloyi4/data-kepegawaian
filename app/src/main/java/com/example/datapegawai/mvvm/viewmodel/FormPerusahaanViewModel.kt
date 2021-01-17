package com.example.datapegawai.mvvm.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.mvvm.model.FormPerusahaanRepository
import com.example.datapegawai.mvvm.model.LandingFormPerusahaanRepository
import com.example.datapegawai.utils.App
import kotlinx.coroutines.launch

class FormPerusahaanViewModel(val repo: FormPerusahaanRepository) : ViewModel() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val idPerusahaan = App.preff.getData(App.keyIdPerusahaan).toString().toInt()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _perusahaanData = MutableLiveData<List<PerusahaanEntity>>()
    val perusahaanData: LiveData<List<PerusahaanEntity>>
        get() = _perusahaanData

    private val _jabatanData = MutableLiveData<List<JabatanEntity>>()
    val jabatanData: LiveData<List<JabatanEntity>>
        get() = _jabatanData

    init {
        getDataPerusahaan()
        getJabatann(idPerusahaan)
    }

    fun getDataPerusahaan(){
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            _perusahaanData.postValue(repo.getDataPerusahaan(idPerusahaan))
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

    fun insertPerusahaan(ctx : Context, perusahaanEntity: PerusahaanEntity) {

        viewModelScope.launch {
            repo.insertPerusahaan(perusahaanEntity)
            App.preff.setDataString(App.keyIdPerusahaan, perusahaanEntity?.id.toString())
            App.preff.setDataString(App.keyNamaPerusahaan, perusahaanEntity?.namaPerusahaan)
            (ctx as Activity).finish()
        }
    }

    fun insertJabatan(jabatanEntity: JabatanEntity) {
        viewModelScope.launch {
            repo.insertJabatan(jabatanEntity)
            getJabatann(idPerusahaan)
        }
    }

    fun deleteJabatan(idJabatanEntity: Int) {
        viewModelScope.launch {
            try {
                var jmlPegawai = repo.getCountPegawaiJabatan(idPerusahaan, idJabatanEntity)
                if (jmlPegawai==0){
                    repo.deleteJabatan(idPerusahaan, idJabatanEntity)
                    getJabatann(idPerusahaan)
                } else {
                    _loadingState.postValue(LoadingState.error("Tidak dapat dihapus karena ada $jmlPegawai pegawai di jabatan ini!"))
                }
            } catch (e: Exception) {
                _loadingState.postValue(LoadingState.error(e.message))
            }
        }
    }

}