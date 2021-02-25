package com.havdulskyi.ailab1.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.UserAnswer
import java.lang.reflect.Type

class RoomTypeConverter {
    @TypeConverter
    fun answersFromString(value: String?): List<Answer> {
        val mapType: Type = object : TypeToken<List<Answer>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun answersToString(value: List<Answer>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun userAnswersFromString(value: String?): List<UserAnswer> {
        val mapType: Type = object : TypeToken<List<UserAnswer>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun userAnswersToString(value: List<UserAnswer>): String {
        val gson = Gson()
        return gson.toJson(value)
    }
}