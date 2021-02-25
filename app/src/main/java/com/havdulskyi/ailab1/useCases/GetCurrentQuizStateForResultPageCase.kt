package com.havdulskyi.ailab1.useCases

import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCurrentQuizStateForResultPageCase(private val repositoryFacade: RepositoryFacade) : BaseUseCase<String?, Flow<QuizState?>>() {
    override suspend fun invoke(vararg param: String?): Flow<QuizState?> {
        return repositoryFacade.getUserAsFlow().flatMapConcat { user ->
            val answers = user?.userAnswers
            flow {
                emit(if (answers == null || answers.isEmpty())
                    QuizState.QuizOpened
                else
                    QuizState.ResultOpened)
            }
        }.flowOn(Dispatchers.IO)
    }
}