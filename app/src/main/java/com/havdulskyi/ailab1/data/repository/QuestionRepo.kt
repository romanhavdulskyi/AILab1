package com.havdulskyi.ailab1.data.repository

import com.havdulskyi.ailab1.data.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepo {
    suspend fun insertQuestion(question: Question)

    suspend fun updateQuestion(question: Question)

    suspend fun removeQuestion(question: Question)

    suspend fun getAllQuestionsAsFlow() : Flow<List<Question>>
}