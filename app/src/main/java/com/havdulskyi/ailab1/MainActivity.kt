package com.havdulskyi.ailab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.havdulskyi.ailab1.navigation.QuizNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val quizNavigator : QuizNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizNavigator.init(this)
    }
}