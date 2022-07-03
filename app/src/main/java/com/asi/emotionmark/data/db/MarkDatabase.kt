package com.asi.emotionmark.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.asi.emotionmark.data.model.EmotionMark

@Database(entities = [EmotionMark::class], version = 1, exportSchema = false)
@TypeConverters(MarkTypeConverters::class)
abstract class MarkDatabase : RoomDatabase() {
    abstract fun markDao(): MarkDao
}