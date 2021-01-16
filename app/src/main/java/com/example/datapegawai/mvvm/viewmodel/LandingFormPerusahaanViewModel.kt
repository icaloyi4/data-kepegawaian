package com.example.datapegawai.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.entity.JabatanEntity
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.mvvm.model.LandingFormPerusahaanRepository
import kotlinx.coroutines.launch

class LandingFormPerusahaanViewModel(val repo: LandingFormPerusahaanRepository) : ViewModel() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState


    fun insertPerusahaan(perusahaanEntity: PerusahaanEntity): MutableLiveData<Long> {
        val liveData = MutableLiveData<Long>()
        viewModelScope.launch {
            liveData.value = repo.insertPerusahaan(perusahaanEntity)
        }
        return liveData
    }

    fun insertJabatan(jabatanEntity: List<JabatanEntity>) {
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            repo.insertJabatan(jabatanEntity)
            _loadingState.postValue(LoadingState.LOADED)
        }
    }

}