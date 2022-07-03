package com.asi.emotionmark.presentation.ui.mark_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarksViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarksViewModel() as T
    }
}