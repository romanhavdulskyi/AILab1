package com.havdulskyi.ailab1.navigation

import androidx.appcompat.app.AppCompatActivity

interface QuizNavigator {
    fun init(appCompatActivity: AppCompatActivity);

    fun navigateBack()
    fun navigateToQuizPage(category : String?)
    fun navigateToResultPage(category : String?)
    fun navigateQuizPageToResultPage(category : String?)
    fun navigateToCategoryPage()

    enum class NavOption {
        Category
    }
}