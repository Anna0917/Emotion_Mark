package com.asi.emotionmark.domain.repository

import androidx.lifecycle.LiveData
import com.asi.emotionmark.data.model.EmotionMark
import java.util.*

interface MarkRepository {
    fun getMarks(): LiveData<List<EmotionMark>>
    fun getMark(id: UUID): LiveData<EmotionMark?>
    fun updateMark(mark: EmotionMark)
    fun addMark(mark: EmotionMark)
    fun deleteMark(mark: EmotionMark)
}