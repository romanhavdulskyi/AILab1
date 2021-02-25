package com.havdulskyi.ailab1.data.repository

import com.havdulskyi.ailab1.data.dao.UserDao
import com.havdulskyi.ailab1.data.model.User
import com.havdulskyi.ailab1.login.Credential
import com.havdulskyi.ailab1.login.CredentialManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

@FlowPreview
@ExperimentalCoroutinesApi
class UserRepoImpl(
    private val userDao: UserDao,
    private val credentialManager: CredentialManager
) : UserRepo {
    override suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO)
        {
            userDao.insert(user)
        }
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO)
        {
            userDao.update(user)
        }
    }

    override suspend fun removeUser(user: User) {
        withContext(Dispatchers.IO)
        {
            userDao.delete(user)
        }
    }

    override suspend fun getUser(name: String, pass: String): User? {
        return withContext(Dispatchers.IO)
        {
            userDao.getUser(name, pass)
        }
    }

    override suspend fun getCurrUserAsFlow(): Flow<User?> {
        return credentialManager.credentialFlow
                .flatMapConcat { value: Credential ->
                    userDao.getUserAsFlow(value.name, value.pass)
                        ?.flowOn(Dispatchers.IO)
                        ?: emptyFlow()
                }
    }
}