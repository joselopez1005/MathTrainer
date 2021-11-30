package com.jlopez.mathtrainer.repository

import com.jlopez.mathtrainer.remote.responses.MathQuestion
import com.jlopez.mathtrainer.remote.xMathApi
import com.jlopez.mathtrainer.util.Resource
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MathRepository @Inject constructor(
    private val api: xMathApi
) {
    suspend fun getMathRandom(): Resource<MathQuestion> {
        val response = try {
            api.getRandomQuestion()
        } catch(e: Exception) {
            return Resource.Error("An Unknown Error Has Occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getMathAdd(): Resource<MathQuestion> {
        val response = try {
            api.getAddQuestion()
        } catch(e: Exception) {
            return Resource.Error("An Unknown Error Has Occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getMathSub(): Resource<MathQuestion> {
        val response = try {
            api.getSubQuestion()
        } catch(e: Exception) {
            return Resource.Error("An Unknown Error Has Occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getMathMul(): Resource<MathQuestion> {
        val response = try {
            api.getMulQuestion()
        } catch(e: Exception) {
            return Resource.Error("An Unknown Error Has Occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getMathDiv(): Resource<MathQuestion> {
        val response = try {
            api.getDivQuestion()
        } catch(e: Exception) {
            return Resource.Error("An Unknown Error Has Occurred")
        }
        return Resource.Success(response)
    }


}