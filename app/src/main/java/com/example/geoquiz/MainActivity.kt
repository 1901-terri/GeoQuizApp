package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = QuizViewModel()

        setContent {
            GeoQuizTheme {
                var screenState by remember { mutableStateOf("home") }

                when (screenState) {
                    "home" -> HomeScreen(onStart = { screenState = "quiz" })
                    "quiz" -> QuizScreen(
                        viewModel = viewModel,
                        onQuizFinished = { screenState = "result" }
                    )
                    "result" -> ResultScreen(
                        score = viewModel.score,
                        total = viewModel.totalQuestions,
                        onRestart = {
                            viewModel.resetQuiz()
                            screenState = "home"
                        }
                    )
                }
            }
        }
    }
}