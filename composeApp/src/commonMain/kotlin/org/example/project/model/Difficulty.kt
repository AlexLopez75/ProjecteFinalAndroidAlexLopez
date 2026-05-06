package org.example.project.model

enum class Difficulty(val pairs: Int, val timeSeconds: Int) {
    EASY(pairs = 6, timeSeconds = 90),
    NORMAL(pairs = 12, timeSeconds = 150),
    HARD(pairs = 20, timeSeconds = 300)
}