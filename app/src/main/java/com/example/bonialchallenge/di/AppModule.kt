package com.example.bonialchallenge.di

import com.example.bonialchallenge.BuildConfig
import com.example.bonialchallenge.network.NetworkClient
import com.example.bonialchallenge.network.NullOnEmptyConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(NetworkClient().build())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

}