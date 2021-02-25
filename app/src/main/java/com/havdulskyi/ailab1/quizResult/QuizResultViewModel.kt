package com.havdulskyi.ailab1.quizResult

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.havdulskyi.ailab1.data.QuizState
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.Question
import com.havdulskyi.ailab1.quizPage.QuizPageViewModel
import com.havdulskyi.ailab1.useCases.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class QuizResultViewModel(
    private val getQuizResultCase: GetQuizResultCase,
    private val getCurrentQuizStateForResultPageCase: GetCurrentQuizStateForResultPageCase,
    private val resetCategoryAnswerStateCase: ResetCategoryAnswerStateCase,
    private val resetAllAnswerStateCase: ResetAllAnswerStateCase
) :
    ViewModel() {

    val quizResults = MutableLiveData<List<QuizResultItemCategoryViewModel>>()
    val currentQuizState = MutableLiveData<QuizState>()


    @RequiresApi(Build.VERSION_CODES.N)
    fun resetQuiz(v: View?) {
        val resTag = v?.tag as QuizResultItemCategoryViewModel?
        resTag?.let { tmp ->
            viewModelScope.launch {
                if (tmp.overallItem)
                    resetAllAnswerStateCase.invoke()
                else
                    resetCategoryAnswerStateCase.invoke(tmp.categoryName)
            }
        }
    }



    init {
        viewModelScope.launch {
            getQuizResultCase.invoke()
                .onEach { value ->
                    quizResults.value = value
                }.launchIn(this)

            getCurrentQuizStateForResultPageCase.invoke()
                    .onEach { value ->
                        currentQuizState.value = value
                    }.launchIn(this)
        }
    }
}