package com.nedashkivskyi.randomuser

import android.app.Application
import com.nedashkivskyi.randomuser.di.module.ApiModule
import com.nedashkivskyi.randomuser.di.component.ApplicationComponent
import com.nedashkivskyi.randomuser.di.component.DaggerApplicationComponent
import com.nedashkivskyi.randomuser.di.module.AppModule
import com.nedashkivskyi.randomuser.di.module.DbModule


class App: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.builder()
        .appModule(AppModule(this))
        .apiModule(ApiModule())
        .dbModule(DbModule())
        .build()
}