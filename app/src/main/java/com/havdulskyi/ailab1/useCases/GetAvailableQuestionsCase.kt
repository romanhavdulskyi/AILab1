package com.havdulskyi.ailab1.useCases

import android.annotation.SuppressLint
import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.data.model.User
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetAvailableQuestionsCase(private val repositoryFacade: RepositoryFacade) :
        BaseUseCase<String?, Flow<List<Question>?>>() {
    @SuppressLint("NewApi")
    override suspend fun invoke(vararg param: String?): Flow<List<Question>?> {
        return repositoryFacade.getAllQuestionsAsFlow().flatMapConcat { value: List<Question> ->
            repositoryFacade.getUserAsFlow().flatMapConcat { user: User? ->
                flow {
                    user?.let {
                        val result = value.toMutableList()
                        result.removeAll { question ->
                            user.userAnswers
                                    .any { it.questionId == question.id }
                                    ||
                                    (param.filterNotNull().isNotEmpty()
                                            && !param.contains(question.category))
                        }
                        emit(
                                result
                        )
                    }

                }
            }
        }.flowOn(Dispatchers.IO)
    }
}