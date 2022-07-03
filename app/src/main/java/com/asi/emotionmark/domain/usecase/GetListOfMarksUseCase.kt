package com.asi.emotionmark.domain.usecase

import androidx.lifecycle.LiveData
import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.domain.repository.MarkRepository

class GetListOfMarksUseCase(private val markRepository: MarkRepository) {

    fun execute(): LiveData<List<EmotionMark>> = markRepository.getMarks()

}