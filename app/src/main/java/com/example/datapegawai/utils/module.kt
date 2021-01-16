package com.example.datapegawai.utils

import android.app.Application
import androidx.room.Room
import com.example.datapegawai.database.AppDatabase
import com.example.datapegawai.database.DataPegawaiDao
import com.example.datapegawai.mvvm.model.LandingRepository
import com.example.datapegawai.mvvm.viewmodel.LandingViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        LandingViewModel(get())
    }
}

val repositoryModule = module {
    fun provideLandingRepository(dao: DataPegawaiDao): LandingRepository {
        return LandingRepository(dao)
    }
    single {
        provideLandingRepository(get())
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
