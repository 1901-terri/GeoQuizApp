package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private val questions = listOf(
        Question("What are contour lines?", listOf(
            "Lines that show roads on a map",
            "Lines that join places of equal rainfall",
            "Lines that join places of equal temperature",
            "Lines that show places of equal height above sea level"
        ), "Lines that show places of equal height above sea level"),

        Question("What does a scale of 1:50 000 mean?", listOf(
            "1 cm on the map = 50 km on the ground",
            "1 cm on the map = 5 m on the ground",
            "1 cm on the map = 500 m on the ground",
            "1 cm on the map = 5 km on the ground"
        ), "1 cm on the map = 500 m on the ground"),

        Question("What is the direction opposite to North-East?", listOf(
            "South-West", "South-West.", "North-West", "South-East"
        ), "South-West"),

        Question("Which map symbol usually represents a railway line?", listOf(
            "A solid red line",
            "A black line with cross-ticks (////)",
            "A blue wavy line",
            "A dashed green line"
        ), "A black line with cross-ticks (////)"),

        Question("What is the purpose of a map key (legend)?", listOf(
            "To explain the symbols used on the map",
            "To show the distance between towns",
            "To give the map’s scale",
            "To show the date the map was drawn"
        ), "To explain the symbols used on the map"),

        Question("Which statement about contour spacing is TRUE?", listOf(
            "Closely spaced contour lines show steep slopes",
            "Widely spaced contour lines show steep slope",
            "Contour lines far apart mean a cliff",
            "Contour lines always mean flat land"
        ), "Closely spaced contour lines show steep slopes"),

        Question("What do spot heights on a map show?", listOf(
            "The average rainfall of an area",
            "The exact height of a specific point above sea level",
            "The temperature of a place",
            "The distance between two points"
        ), "The exact height of a specific point above sea level"),

        Question("Which of the following is NOT usually shown on a topographic map?", listOf(
            "Roads", "Rivers", "Elevation", "Population of a country"
        ), "Population of a country"),

        Question("How many degrees are there in a compass?", listOf(
            "90°", "270°", "360°", "180°"
        ), "360°"),

        Question("What type of map shows natural and man-made features in detail, usually on a large scale?", listOf(
            "Political map", "Topographic map", "Climate map", "Population density map"
        ), "Topographic map")
    )

    var currentQuestionIndex = 0
        private set

    val currentQuestion: Question
        get() = questions[currentQuestionIndex]

    val totalQuestions: Int
        get() = questions.size

    var score = 0
        private set

    var lastAnswerCorrect: Boolean? = null
        private set

    fun submitAnswer(answer: String) {
        lastAnswerCorrect = answer == currentQuestion.correctAnswer
        if (lastAnswerCorrect == true) score++
    }

    fun nextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            lastAnswerCorrect = null
        }
    }

    fun isQuizFinished(): Boolean = currentQuestionIndex == questions.size - 1 && lastAnswerCorrect != null

    fun resetQuiz() {
        currentQuestionIndex = 0
        score = 0
        lastAnswerCorrect = null
    }
}