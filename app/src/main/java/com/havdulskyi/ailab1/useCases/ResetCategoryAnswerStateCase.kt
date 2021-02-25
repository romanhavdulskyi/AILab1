package com.havdulskyi.ailab1.useCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.UserAnswer
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ResetCategoryAnswerStateCase(private val repositoryFacade: RepositoryFacade) :
        BaseUseCase<String, Any>() {
    override suspend fun invoke(vararg category: String): Any {
        val removeCategory = category[0]
        repositoryFacade.getUserAsFlow()
                .take(1)
                .flowOn(Dispatchers.IO)
                .collect { user ->
                    user?.let {
                        it.userAnswers.removeAll { userAnswer ->
                            userAnswer.category == removeCategory
                        }
                        repositoryFacade.updateUser(it)
                    }
                }
        return Any()
    }
}