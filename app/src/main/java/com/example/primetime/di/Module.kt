package com.example.primetime.di

import com.example.primetime.ui.MoviesViewmodel
import com.example.primetime.repository.MoviesRepository
import com.squareup.moshi.Moshi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel { MoviesViewmodel(get()) }

    single { Moshi.Builder().build() }


}

val repositoryModule = module {
    factory { MoviesRepository(get(),get()) }



}
