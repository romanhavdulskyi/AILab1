package com.havdulskyi.ailab1.useCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.UserAnswer
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ResetLastCategoryAnswerStateCase(private val repositoryFacade: RepositoryFacade) :
        BaseUseCase<UserAnswer, Any>() {
    override suspend fun invoke(vararg param: UserAnswer): Any {
        repositoryFacade.getUserAsFlow()
                .take(1)
                .flowOn(Dispatchers.IO)
                .collect { user ->
                    user?.let { currUser ->
                        val lastQuestion = currUser.userAnswers.lastOrNull()
                        lastQuestion?.let { question ->
                            currUser.userAnswers.removeAll { it.category == question.category }
                            repositoryFacade.updateUser(currUser)
                        }
                    }
                }
        return Any()
    }
}