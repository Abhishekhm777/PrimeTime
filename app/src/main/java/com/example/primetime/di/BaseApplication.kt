package com.example.primetime.di

import android.app.Application
import com.example.primetime.di.appModule
import com.example.primetime.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(
                appModule,
                repositoryModule
            ))
        }
    }

}