package org.example.project.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class MemoryCard (
    val uniqueId: String = Uuid.random().toString(),
    val cardEntity: CardEntity,
    val isFaceUp: Boolean = false,
    val isMatched: Boolean = false
)
