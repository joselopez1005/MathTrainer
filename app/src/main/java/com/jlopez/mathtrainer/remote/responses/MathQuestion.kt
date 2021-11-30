package com.jlopez.mathtrainer.remote.responses

data class MathQuestion(
    val answer: Int,
    val expression: String,
    val first: Int,
    val operation: String,
    val second: Int
)