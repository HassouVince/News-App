package fr.systemathicdev.newsapp

import android.app.Application
import fr.systemathicdev.newsapp.di.*
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        initKoin()
        super.onCreate()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                appModule,
                viewModule,
                repositoryModule,
                remoteModule,
                useCaseModule,
                retrofitModule,
            )
        )
    }
}