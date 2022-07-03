package com.asi.emotionmark.domain.usecase

import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.domain.repository.MarkRepository

class SaveMarkDetailByIdUseCase(private val markRepository: MarkRepository) {

    fun execute(param: EmotionMark) = markRepository.updateMark(param)

}