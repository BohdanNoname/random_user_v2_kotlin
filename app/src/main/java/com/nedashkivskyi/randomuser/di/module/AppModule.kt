package com.nedashkivskyi.randomuser.di.module

import android.content.Context
import com.nedashkivskyi.randomuser.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val context: Context) {

    @Singleton
    @Provides
    fun providesBaseContext(): App =
        context as App
}