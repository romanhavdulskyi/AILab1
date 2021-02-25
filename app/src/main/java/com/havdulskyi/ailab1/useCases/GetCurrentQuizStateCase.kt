package com.havdulskyi.ailab1.useCases

import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.User
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetCurrentQuizStateCase(private val getAvailableQuestionsCase: GetAvailableQuestionsCase) : BaseUseCase<String?, Flow<QuizState?>>() {
    override suspend fun invoke(vararg param: String?): Flow<QuizState?> {
        return getAvailableQuestionsCase.invoke(*param).flatMapConcat { available ->
            flow {
                emit(if (available == null || available.isEmpty())
                    QuizState.ResultOpened
                else
                    QuizState.QuizOpened)
            }
        }.flowOn(Dispatchers.IO)
    }
}