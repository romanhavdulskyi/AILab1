package com.havdulskyi.ailab1.useCases

import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.UserAnswer
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ResetAllAnswerStateCase(private val repositoryFacade: RepositoryFacade) :
        BaseUseCase<Any, Any>() {
    override suspend fun invoke(vararg param: Any): Any {
        repositoryFacade.getUserAsFlow()
                .take(1)
                .flowOn(Dispatchers.IO)
                .collect { user ->
                    user?.let {
                        it.userAnswers.clear()
                        repositoryFacade.updateUser(it)
                    }
                }
        return Any()
    }
}