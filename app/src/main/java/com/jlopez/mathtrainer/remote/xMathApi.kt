package com.jlopez.mathtrainer.remote

import com.jlopez.mathtrainer.remote.responses.MathQuestion
import retrofit2.http.GET

interface xMathApi {
    @GET("Random")
    suspend fun getRandomQuestion(): MathQuestion

    @GET("add")
    suspend fun getAddQuestion(): MathQuestion

    @GET("sub")
    suspend fun getSubQuestion(): MathQuestion

    @GET("mul")
    suspend fun getMulQuestion(): MathQuestion

    @GET("div")
    suspend fun getDivQuestion(): MathQuestion

}