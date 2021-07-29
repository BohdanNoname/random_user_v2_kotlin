package com.nedashkivskyi.randomuser.di.module

import com.nedashkivskyi.randomuser.BuildConfig
import com.nedashkivskyi.randomuser.api.ApiService
import com.nedashkivskyi.randomuser.repository.apiRepo.ApiRepository
import com.nedashkivskyi.randomuser.repository.apiRepo.ApiRepositoryImpl
import com.nedashkivskyi.randomuser.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    fun providesBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun providesOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesApiRepository(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository =
        apiRepositoryImpl

    @Singleton
    @Provides
    fun provideApiRepositoryImpl(apiService: ApiService): ApiRepositoryImpl = ApiRepositoryImpl(apiService)
}