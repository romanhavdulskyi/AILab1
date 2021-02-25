package com.havdulskyi.ailab1.useCases

import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import com.havdulskyi.ailab1.login.CredentialManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class LoginUserCase(private val repositoryFacade: RepositoryFacade,
                    private val credentialManager: CredentialManager) : BaseUseCase<String, Flow<Boolean>>() {
    override suspend fun invoke(vararg param: String): Flow<Boolean> {
        val name = param[0]
        val pass = param[1]
        return flow {
            val result = repositoryFacade.getUser(name, pass) != null
            if (result)
                credentialManager.putCredential(name, pass)
            else
                credentialManager.clearCredential()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}