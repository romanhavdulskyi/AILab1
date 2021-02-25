package com.havdulskyi.ailab1.useCases

import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.data.model.UserAnswer
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take

class SaveAnswerStateCase(private val repositoryFacade: RepositoryFacade) :
        BaseUseCaseTwoParam<Answer, Question, Any>() {
    override suspend fun invoke(answer: Answer, question: Question): Any {
        val userAnswer = UserAnswer(question.id, question.category, answer.answerId)
        repositoryFacade.getUserAsFlow()
                .take(1)
                .collect { user ->
                    user?.let {
                        it.userAnswers.add(userAnswer)
                        repositoryFacade.updateUser(it)
                    }
                }

        return Any()
    }
}