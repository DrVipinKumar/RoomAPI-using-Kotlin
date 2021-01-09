package com.example.myroomapiexample

import androidx.room.*

@Dao
interface MyUserDao {
    @Query("select * from UserTable")
    fun userInfo(): List<User>

    @Insert
    fun insertRecord(user: User)

    @Update
    fun updateRecord(user: User)

    @Delete
    fun deleteRecord(user: User)
}