package com.nedashkivskyi.randomuser.di.component

import android.content.Context
import com.nedashkivskyi.randomuser.di.module.ApiModule
import com.nedashkivskyi.randomuser.di.module.AppModule
import com.nedashkivskyi.randomuser.di.module.DbModule
import com.nedashkivskyi.randomuser.di.subcomponent.MainSubcomponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, AppModule::class, DbModule::class])
interface ApplicationComponent {
    val mainSubcomponent: MainSubcomponent.Factory
}