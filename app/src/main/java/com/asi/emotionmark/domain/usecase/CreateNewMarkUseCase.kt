package com.asi.emotionmark.domain.usecase

import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.domain.repository.MarkRepository
import java.util.*

class CreateNewMarkUseCase(private val markRepository: MarkRepository) {
    fun execute(mark: EmotionMark) = markRepository.addMark(mark)
}