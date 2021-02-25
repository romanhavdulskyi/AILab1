package com.havdulskyi.ailab1.quizChooseCategory

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
import com.havdulskyi.ailab1.navigation.QuizNavigator
import com.havdulskyi.ailab1.useCases.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class QuizChooseCategoryViewModel(private val getCurrentQuizStateCase: GetCurrentQuizStateCase,
                                  private val quizNavigator: QuizNavigator) :
    ViewModel() {

    val currentQuizState = MutableLiveData<QuizState>()

    fun navigateToQuizPage(v : View?)
    {
        val category = v?.tag?.toString()
        Log.e("Debug", "Nav to quiz page $category")
        quizNavigator.navigateToQuizPage(category)
    }

    init {
        viewModelScope.launch {
            getCurrentQuizStateCase.invoke()
                .onEach { state ->
                    if(currentQuizState.value != state)
                        currentQuizState.value = state
                    Log.e("Debug", "New quiz state $state")
                }
                .launchIn(this)
        }
    }
}