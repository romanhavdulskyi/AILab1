package com.havdulskyi.ailab1.navigation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.havdulskyi.ailab1.R

class QuizNavigatorImpl : QuizNavigator {
    private var navHostFragment: NavHostFragment? = null
    private var navController: NavController? = null

    override fun init(appCompatActivity: AppCompatActivity) {
        navHostFragment = appCompatActivity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment?.navController
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateToQuizPage(category : String?) {
        val bundle = Bundle()
        category?.let {
            bundle.putString(QuizNavigator.NavOption.Category.toString(), category)
        }
        navController?.navigate(R.id.quizPageFragment, bundle)
    }

    override fun navigateToResultPage(category : String?) {
        Log.e("Debug", "Nav to res page")
        val bundle = Bundle()
        category?.let {
            bundle.putString(QuizNavigator.NavOption.Category.toString(), category)
        }
        navController?.navigate(R.id.quizResultFragment, bundle)
    }

    override fun navigateQuizPageToResultPage(category: String?) {
        Log.e("Debug", "Nav to res page")
        val bundle = Bundle()
        category?.let {
            bundle.putString(QuizNavigator.NavOption.Category.toString(), category)
        }
        navController?.navigate(R.id.action_quizPageFragment_to_quizResultFragment, bundle)
    }

    override fun navigateToCategoryPage() {
        navController?.navigate(R.id.quizChooseCategoryFragment)
    }

}