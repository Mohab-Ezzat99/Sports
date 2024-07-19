package com.app.sports.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    @ColumnInfo(name = "createdAt", defaultValue = "0")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "termGrade", defaultValue = "0")
    val termGrade: Int = 100
)
