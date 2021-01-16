package com.example.datapegawai.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datapegawai.database.LoadingState
import com.example.datapegawai.database.entity.PerusahaanEntity
import com.example.datapegawai.mvvm.model.LandingRepository
import kotlinx.coroutines.launch

class LandingViewModel(val repo: LandingRepository) : ViewModel() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _perusahaanData = MutableLiveData<Int>()
    val perusahaanData: LiveData<Int>
        get() = _perusahaanData

    fun getDataPerusahaan(){
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            _perusahaanData.postValue(repo.getDataPerusahaan())
            _loadingState.postValue(LoadingState.LOADED)
        }

    }

}