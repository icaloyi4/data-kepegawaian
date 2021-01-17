package com.example.datapegawai.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.mvvm.model.LandingPerusahaanRepository
import com.example.datapegawai.mvvm.model.LandingRepository
import kotlinx.coroutines.launch

class LandingPerusahaanViewModel(val repo: LandingPerusahaanRepository) : ViewModel() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _perusahaanData = MutableLiveData<List<PerusahaanEntity>>()
    val perusahaanData: LiveData<List<PerusahaanEntity>>
        get() = _perusahaanData

    init {
        getDataPerusahaan()
    }
    fun getDataPerusahaan(){
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            _perusahaanData.postValue(repo.getDataPerusahaan())
            _loadingState.postValue(LoadingState.LOADED)
        }
    }

}