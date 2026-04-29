package org.example.project.model

enum class Difficulty(val pairs: Int, val timeSeconds: Int) {
    EASY(pairs = 6, timeSeconds = 50),
    NORMAL(pairs = 12, timeSeconds = 90),
    HARD(pairs = 25, timeSeconds = 150)
}