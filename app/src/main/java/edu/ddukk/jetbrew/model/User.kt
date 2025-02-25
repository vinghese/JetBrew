package edu.ddukk.jetbrew.model

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val password: String
)