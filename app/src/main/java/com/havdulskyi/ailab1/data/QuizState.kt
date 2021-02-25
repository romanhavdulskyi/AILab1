package com.havdulskyi.ailab1.data

sealed class QuizState{
    object QuizOpened : QuizState()
    object ResultOpened : QuizState()
}
