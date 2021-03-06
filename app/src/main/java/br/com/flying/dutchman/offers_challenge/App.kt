package br.com.flying.dutchman.offers_challenge

import android.app.Application
import br.com.flying.dutchman.offers_challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        fun offersJson(): String {
            return instance.assets.open("offers.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, repositoryModule, netModule, apiModule, databaseModule))
        }
    }
}