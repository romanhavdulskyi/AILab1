package com.havdulskyi.ailab1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.havdulskyi.ailab1.data.model.Answer
import com.havdulskyi.ailab1.data.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun delete(question: Question)

    @Query("SELECT * FROM Question")
    fun allQuestionsAsFlow() : Flow<List<Question>>
}
