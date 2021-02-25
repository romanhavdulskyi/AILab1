package com.havdulskyi.ailab1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.havdulskyi.ailab1.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun delete(user: User)

    @Query("SELECT * FROM User WHERE name = :name AND pass = :pass")
    fun getUser(name : String, pass : String) : User?

    @Query("SELECT * FROM User WHERE name = :name AND pass = :pass")
    fun getUserAsFlow(name : String, pass : String) : Flow<User>?
}