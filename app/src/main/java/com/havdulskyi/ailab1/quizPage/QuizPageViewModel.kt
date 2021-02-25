package com.havdulskyi.ailab1.quizPage

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.useCases.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class QuizPageViewModel(private val getCurrentQuizStateCase: GetCurrentQuizStateCase,
                        private val getAvailableQuestionsCase: GetAvailableQuestionsCase,
                        private val resetCategoryAnswerStateCase: ResetCategoryAnswerStateCase,
                        private val resetAllAnswerStateCase: ResetAllAnswerStateCase,
                        private val saveAnswerStateCase: SaveAnswerStateCase) :
    ViewModel() {

    val requestedCategory = ObservableField<String?>()
    val currentQuestion = ObservableField<Question?>()
    val currentQuizState = MutableLiveData<QuizState>()

    fun saveAnswer(v : View?)
    {
        viewModelScope.launch {
            val tagAnswer = v?.tag as Answer?
            tagAnswer?.let { answer ->
                currentQuestion.get()?.let { saveAnswerStateCase.invoke(answer, it) }
            }
        }
    }

    fun resetAll(v : View?)
    {
        viewModelScope.launch {
          resetAllAnswerStateCase.invoke()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun resetCurrCategory(v : View?)
    {
        viewModelScope.launch {
            currentQuestion.get()?.category?.let { resetAllAnswerStateCase.invoke(it) }
        }
    }

    fun loadCategory(category: String?)
    {
        viewModelScope.launch {

            category?.let {
                requestedCategory.set(category)
            }
            getAvailableQuestionsCase.invoke(category)
                    .onEach { list: List<Question>? ->
                        val first = list?.firstOrNull()
                        currentQuestion.set(first)
                    }.launchIn(this)

                getCurrentQuizStateCase.invoke(category)
                        .onEach { state ->
                            if(currentQuizState.value != state) {
                                currentQuizState.value = state
                                Log.e("Debug", "New quiz state $state")
                            }
                        }
                        .launchIn(this)
            }
    }

    init {

    }
}