package com.havdulskyi.ailab1.data.repository

import com.havdulskyi.ailab1.data.dao.QuestionDao
import com.havdulskyi.ailab1.data.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class QuestionRepoImpl(private val questionDao: QuestionDao) : QuestionRepo {
    override suspend fun insertQuestion(question: Question) {
        withContext(Dispatchers.IO)
        {
            questionDao.insert(question)
        }
    }

    override suspend fun updateQuestion(question: Question) {
        withContext(Dispatchers.IO)
        {
            questionDao.update(question)
        }
    }

    override suspend fun removeQuestion(question: Question) {
        withContext(Dispatchers.IO)
        {
            questionDao.delete(question)
        }
    }

    override suspend fun getAllQuestionsAsFlow(): Flow<List<Question>> {
        return questionDao.allQuestionsAsFlow()
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
    }
}