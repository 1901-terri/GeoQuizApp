package com.example.geoquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuizScreen(viewModel: QuizViewModel, onQuizFinished: () -> Unit) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showFeedback by remember { mutableStateOf(false) }

    val question = viewModel.currentQuestion
    val progress = (viewModel.currentQuestionIndex + 1).toFloat() / viewModel.totalQuestions.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "GeoQuiz",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color(0xFF6200EE),
            trackColor = Color(0xFFE0E0E0)
        )

        Text(
            text = "Question ${viewModel.currentQuestionIndex + 1} of ${viewModel.totalQuestions}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = question.text,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!showFeedback) {
            question.options.forEach { option ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFBBDEFB), RoundedCornerShape(12.dp))
                        .clickable {
                            selectedAnswer = option
                            showFeedback = true
                        }
                        .padding(16.dp)
                ) {
                    Text(text = option, style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        if (showFeedback && selectedAnswer != null) {
            val isCorrect = selectedAnswer == question.correctAnswer
            Text(
                text = if (isCorrect) "✅ Correct!" else "❌ Incorrect",
                style = MaterialTheme.typography.bodyLarge,
                color = if (isCorrect) Color(0xFF388E3C) else Color(0xFFD32F2F)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.submitAnswer(selectedAnswer!!)
                    if (viewModel.isQuizFinished()) {
                        onQuizFinished()
                    } else {
                        viewModel.nextQuestion()
                        selectedAnswer = null
                        showFeedback = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Next", color = Color.White)
            }
        }
    }
}