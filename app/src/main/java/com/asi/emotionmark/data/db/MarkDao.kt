package com.asi.emotionmark.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.asi.emotionmark.data.model.EmotionMark
import java.util.*

@Dao
interface MarkDao {
    @Query("SELECT * FROM emotionMark")
    fun getMarks(): LiveData<List<EmotionMark>>
    @Query("SELECT * FROM emotionMark WHERE id=(:id)")
    fun getMark(id: UUID): LiveData<EmotionMark?>
    @Update
    fun updateMark(mark: EmotionMark)
    @Insert
    fun addMark(mark: EmotionMark)
    @Delete
    fun deleteMark(mark: EmotionMark)
}