package com.example.datapegawai.utils

import android.app.Application
import androidx.room.Room
import com.example.datapegawai.database.AppDatabase
import com.example.datapegawai.database.DataPegawaiDao
import com.example.datapegawai.mvvm.model.*
import com.example.datapegawai.mvvm.viewmodel.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        LandingViewModel(get())
    }

    viewModel {
        LandingFormPerusahaanViewModel(get())
    }

    viewModel {
        FormPerusahaanViewModel(get())
    }

    viewModel {
        LandingPerusahaanViewModel(get())
    }

    viewModel {
        MainViewModel(get())
    }
}

val repositoryModule = module {
    fun provideLandingFormPerusahaanRepository(dao: DataPegawaiDao): LandingFormPerusahaanRepository {
        return LandingFormPerusahaanRepository(dao)
    }
    single {
        provideLandingFormPerusahaanRepository(get())
    }

    fun provideFormPerusahaanRepository(dao: DataPegawaiDao): FormPerusahaanRepository {
        return FormPerusahaanRepository(dao)
    }
    single {
        provideFormPerusahaanRepository(get())
    }

    fun provideLandingRepository(dao: DataPegawaiDao): LandingRepository {
        return LandingRepository(dao)
    }
    single {
        provideLandingRepository(get())
    }

    fun provideLandingPerusahaanRepository(dao: DataPegawaiDao): LandingPerusahaanRepository {
        return LandingPerusahaanRepository(dao)
    }
    single {
        provideLandingPerusahaanRepository(get())
    }

    fun provideMainRepository(dao: DataPegawaiDao): MainRepository {
        return MainRepository(dao)
    }
    single {
        provideMainRepository(get())
    }
}

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "datapegawai.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: AppDatabase): DataPegawaiDao {
        return database.dataPegawaiDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}
