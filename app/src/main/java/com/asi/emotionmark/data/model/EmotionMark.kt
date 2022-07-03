package com.asi.emotionmark.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class EmotionMark(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val date: Date = Date(),
    var rating: Int = 0, // по шкале от 0 до 10
    var emotion: String = "",
    var description: String = ""
)
