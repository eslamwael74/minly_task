package com.eslamwael74.minlyTask.di

import com.eslamwael74.minlyTask.BuildConfig
import com.eslamwael74.minlyTask.data.remote.ImageRemoteDataSource
import com.eslamwael74.minlyTask.data.remote.ImageService
import com.eslamwael74.minlyTask.data.repository.ImageRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by eslamwael74 on 5/18/2021.
 * Email: eslamwael74@outlook.com
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Singleton
    @Provides
    fun provideImageRemoteDataSource(imageService: ImageService) =
        ImageRemoteDataSource(imageService)

    @Provides
    fun provideImageService(retrofit: Retrofit): ImageService =
        retrofit.create(ImageService::class.java)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ImageRemoteDataSource) =
        ImageRepository(remoteDataSource)
}