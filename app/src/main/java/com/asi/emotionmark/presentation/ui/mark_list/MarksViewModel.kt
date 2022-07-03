package com.asi.emotionmark.presentation.ui.mark_list

import androidx.lifecycle.ViewModel
import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.data.repository.MarkRepositoryImpl
import com.asi.emotionmark.domain.usecase.CreateNewMarkUseCase
import com.asi.emotionmark.domain.usecase.GetListOfMarksUseCase

class MarksViewModel : ViewModel() {
    private val getListOfMarksUC = GetListOfMarksUseCase(MarkRepositoryImpl.get())
    val markList = getListOfMarksUC.execute()

//    private val createNewMarkUseCase = CreateNewMarkUseCase(MarkRepositoryImpl.get())
//    private val emotions = arrayOf("Грустно", "Весело", "Тревожно", "Устало")
//    init {
//        for (i in 0 until 30) {
//            val mark = EmotionMark()
//            mark.rating = i % 11
//            mark.emotion = emotions[i % emotions.size]
//            createNewMarkUseCase.execute(mark)
//        }
//    }
}