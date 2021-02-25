package com.havdulskyi.ailab1.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey(autoGenerate = true)
                val id : Int,
                val name : String,
                val pass : String,
                val currentQuestion : String,
                val userAnswers : MutableList<UserAnswer>) {
}