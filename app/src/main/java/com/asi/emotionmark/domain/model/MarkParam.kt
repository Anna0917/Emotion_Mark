package com.asi.emotionmark.domain.model

import java.util.*

data class MarkParam(
    val id: UUID, // UUID
    val date: Date,
    var rating: Int, // по шкале от 0 до 10
    var emotion: String,
    var description: String
)