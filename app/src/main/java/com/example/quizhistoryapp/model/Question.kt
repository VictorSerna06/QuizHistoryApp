package com.example.quizhistoryapp.model

data class Question(
    val text: String,
    val options: List<String>,
    val answer: Int,
)