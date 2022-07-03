package com.asi.emotionmark.domain.usecase

import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.domain.repository.MarkRepository
import java.util.*

class GetMarkDetailByIdUseCase(private val markRepository: MarkRepository) {

    fun execute(id: UUID) = markRepository.getMark(id)

}