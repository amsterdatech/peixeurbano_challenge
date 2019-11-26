package br.com.flying.dutchman.offers_challenge

import android.app.Application

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
    }


}