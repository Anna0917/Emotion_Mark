package com.asi.emotionmark.data.db

import androidx.room.TypeConverter
import java.util.*

class MarkTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?): Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisecondsEpoch: Long?): Date?{
        return millisecondsEpoch?.let{
            Date(it)
        }
    }

    @TypeConverter
    fun fromUUID(uuid: String?): UUID?{
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun toUUID(uuid: UUID?): String?{
        return uuid?.toString()
    }
}