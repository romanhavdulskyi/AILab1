package com.havdulskyi.ailab1.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(@PrimaryKey val id : String,
                    val questionString : String,
                    val category : String,
                    val answers : List<Answer> )
