package com.marshall.therickandmorty

import android.app.Application
import com.marshall.therickandmorty.character.di.characterModule
import com.marshall.therickandmorty.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(networkModule, characterModule)
        }
    }
}