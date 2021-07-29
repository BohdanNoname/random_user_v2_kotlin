package com.nedashkivskyi.randomuser.di.module

import com.nedashkivskyi.randomuser.App
import com.nedashkivskyi.randomuser.db.AppDatabase
import com.nedashkivskyi.randomuser.db.PersonDao
import com.nedashkivskyi.randomuser.repository.dbRepo.DbRepository
import com.nedashkivskyi.randomuser.repository.dbRepo.DbRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun providesPersonDao(app: App): PersonDao = AppDatabase.getDb(app.baseContext).personDao()

    @Singleton
    @Provides
    fun providesDbRepository(dbRepositoryImpl: DbRepositoryImpl): DbRepository = dbRepositoryImpl

    @Singleton
    @Provides
    fun providesDbRepositoryImpl(personDao: PersonDao): DbRepositoryImpl = DbRepositoryImpl(personDao)
}