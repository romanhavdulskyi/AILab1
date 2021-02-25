package com.havdulskyi.ailab1.data.repository

import androidx.core.util.Supplier
import com.havdulskyi.ailab1.data.QuizDB
import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.data.model.User
import com.havdulskyi.ailab1.login.CredentialManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn

class RepositoryFacade @ExperimentalCoroutinesApi constructor(
    dbSupplier : Supplier<QuizDB>,
    private val credentialManager: CredentialManager) {
    private val questionRepo = dbSupplier.get().questionDao()?.let { QuestionRepoImpl(it) }
    @FlowPreview
    private val userRepo = dbSupplier.get().userDao()?.let { UserRepoImpl(it, credentialManager) }

    suspend fun insertQuestion(question: Question)
    {
        questionRepo?.insertQuestion(question)
    }

    suspend fun updateQuestion(question: Question)
    {
        questionRepo?.updateQuestion(question)
    }

    suspend fun removeQuestion(question: Question)
    {
        questionRepo?.removeQuestion(question)
    }

    suspend fun getAllQuestionsAsFlow() : Flow<List<Question>>
    {
        return questionRepo?.getAllQuestionsAsFlow() ?: emptyFlow()
    }

    suspend fun insertUser(user: User)
    {
        userRepo?.insertUser(user)
    }

    suspend fun updateUser(user: User)
    {
        userRepo?.updateUser(user)
    }

    suspend fun removeUser(user: User)
    {
        userRepo?.removeUser(user)
    }

    suspend fun getUser(name : String, pass : String) : User?
    {
        return userRepo?.getUser(name, pass)
    }

    suspend fun getUserAsFlow(): Flow<User?> {
        return userRepo?.getCurrUserAsFlow() ?: emptyFlow()
    }
}