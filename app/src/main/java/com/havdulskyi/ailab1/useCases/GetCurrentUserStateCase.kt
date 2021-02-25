package com.havdulskyi.ailab1.useCases

import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.User
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetCurrentUserStateCase(private val repositoryFacade: RepositoryFacade) :
        BaseUseCase<String, Flow<User?>>() {
    override suspend fun invoke(vararg param: String): Flow<User?> {
        return repositoryFacade.getUserAsFlow()
                .flowOn(Dispatchers.IO)
    }
}