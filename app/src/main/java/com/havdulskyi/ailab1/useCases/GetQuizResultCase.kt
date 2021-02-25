package com.havdulskyi.ailab1.useCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.data.model.User
import com.havdulskyi.ailab1.data.model.UserAnswer
import com.havdulskyi.ailab1.data.repository.RepositoryFacade
import com.havdulskyi.ailab1.quizResult.QuizResultItemCategoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetQuizResultCase(private val repositoryFacade: RepositoryFacade) :
        BaseUseCase<Any, Flow<List<QuizResultItemCategoryViewModel>?>>() {
    override suspend fun invoke(vararg param: Any): Flow<List<QuizResultItemCategoryViewModel>?> {
        return flow {
            val questions = repositoryFacade.getAllQuestionsAsFlow()
                    .take(1)
                    .toList()
                    .flatten()

            repositoryFacade.getUserAsFlow().collect { user: User? ->
                user?.let {
                    val resList = mutableListOf<QuizResultItemCategoryViewModel>()
                    val userAnswers = user.userAnswers
                    val grouped = questions.groupBy { it.category }

                    val overallResult = QuizResultItemCategoryViewModel("Overall",
                            calculateOverallPoints(userAnswers, questions, "").toString(),
                            grouped.map {
                                Pair(
                                        it.key,
                                        calculateOverallPoints(userAnswers, questions, it.key).toFloat()
                                )
                            }, true)

                    resList.add(overallResult)
                    resList.addAll(grouped.map {
                        QuizResultItemCategoryViewModel(it.key,
                                calculateOverallPoints(userAnswers, it.value, it.key).toString(),
                                it.value.mapIndexed { index, question ->
                                    Pair(index.toString(), calculatePointsForGroup(question, userAnswers))
                                }, false)
                    })
                    resList.removeAll { it.overallResult.toInt() == 0 }

                    emit(resList)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun calculateOverallPoints(
            userAnswer: List<UserAnswer>,
            questions: List<Question>,
            category: String
    ): Int {
        return userAnswer.map { userAnswer ->
            questions
                    .asSequence()
                    .filter { it.id == userAnswer.questionId }
                    .filter { category.isEmpty() || it.category == category }
                    .map { it.answers }
                    .flatten()
                    .firstOrNull { it.answerId == userAnswer.answerId }
                    ?.point ?: 0
        }.sum()
    }

    private fun calculatePointsForGroup(question: Question, userAnswers: List<UserAnswer>): Float {
        return (question.answers.find { answer ->
            answer.answerId == userAnswers
                    .find { userAnswer -> userAnswer.questionId == question.id }
                    ?.answerId
        }?.point?.toFloat() ?: 0F) + 1F
    }
}