package com.jlopez.mathtrainer.remote.di

import com.jlopez.mathtrainer.remote.xMathApi
import com.jlopez.mathtrainer.repository.MathRepository
import com.jlopez.mathtrainer.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMathRepository(
        api: xMathApi
    ) = MathRepository(api)

    @Singleton
    @Provides
    fun provideMathApi(): xMathApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(xMathApi::class.java)
    }
}