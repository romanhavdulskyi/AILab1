package com.havdulskyi.ailab1.data.repository

import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun removeUser(user: User)

    suspend fun getUser(name : String, pass : String) : User?

    suspend fun getCurrUserAsFlow() : Flow<User?>
}