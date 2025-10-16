package com.example.geoquiz

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)