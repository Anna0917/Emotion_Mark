package com.asi.emotionmark.presentation.ui.mark_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarkDetailViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarkDetailViewModel() as T
    }
}