package com.asi.emotionmark.presentation.ui.mark_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.data.repository.MarkRepositoryImpl
import com.asi.emotionmark.domain.usecase.CreateNewMarkUseCase
import com.asi.emotionmark.domain.usecase.GetMarkDetailByIdUseCase
import com.asi.emotionmark.domain.usecase.SaveMarkDetailByIdUseCase
import java.util.*

class MarkDetailViewModel : ViewModel() {
    private val markRepository = MarkRepositoryImpl.get()
    private val getMarkDetailByIdUC = GetMarkDetailByIdUseCase(markRepository)
    private val saveMarkDetailByIdUC = SaveMarkDetailByIdUseCase(markRepository)
    private val createNewMarkUC = CreateNewMarkUseCase(markRepository)

    private val markIdLiveData = MutableLiveData<UUID>()
    var markLiveData: LiveData<EmotionMark?> =
        Transformations.switchMap(markIdLiveData) { markId ->
            getMarkDetailByIdUC.execute(markId)
        }

    fun loadMark(markId: UUID){
        markIdLiveData.value = markId
    }

    fun saveMark(mark: EmotionMark){
        saveMarkDetailByIdUC.execute(mark)
    }

    fun addMark(mark: EmotionMark){
        createNewMarkUC.execute(mark)
    }
}