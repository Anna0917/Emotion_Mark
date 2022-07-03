package com.asi.emotionmark.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.asi.emotionmark.data.db.MarkDatabase
import com.asi.emotionmark.data.model.EmotionMark
import com.asi.emotionmark.domain.repository.MarkRepository
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "mark-database"

class MarkRepositoryImpl private constructor(context: Context) : MarkRepository {
    private val database: MarkDatabase = Room.databaseBuilder(
        context.applicationContext,
        MarkDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val markDao = database.markDao()
    private val executor = Executors.newSingleThreadExecutor()


    override fun getMarks(): LiveData<List<EmotionMark>> = markDao.getMarks()

    override fun getMark(id: UUID): LiveData<EmotionMark?> = markDao.getMark(id)

    override fun updateMark(mark: EmotionMark) {
        executor.execute { markDao.updateMark(mark) }
    }

    override fun addMark(mark: EmotionMark) {
        executor.execute { markDao.addMark(mark) }
    }

    override fun deleteMark(mark: EmotionMark) {
        executor.execute { markDao.deleteMark(mark) }
    }


    companion object {

        private var INSTANCE: MarkRepositoryImpl? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MarkRepositoryImpl(context)
            }
        }

        fun get(): MarkRepositoryImpl {
            return INSTANCE ?: throw IllegalStateException("MArkRepository must be initialized")
        }
    }
}