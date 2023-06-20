package com.claratoll.tabatatimer

data class Exercise(
    val name: String = "Exercise",
    val workoutNumber: Int = 20,
    val restNumber: Int = 10,
    val rounds: Int = 8,
    val goOrRest: Boolean = true
)
