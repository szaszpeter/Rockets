package com.codarchy.data.di

import android.content.Context
import com.codarchy.data.network.AgeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Singleton
    @Provides
    fun provideAgeApi(okHttpClient: OkHttpClient): AgeApi = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl("https://api.agify.io")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(AgeApi::class.java)

}
