package com.example.myroomapiexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User(
    @PrimaryKey var uid:Int,
    @ColumnInfo(name = "full_name") var name: String?,
    @ColumnInfo(name = "password")  var pwd: String?
)