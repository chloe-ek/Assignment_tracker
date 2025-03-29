package com.bcit.assignmenttracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assignment")
data class Assignment (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val course: String,
    val deadline: String,
    val courseLevel: Int,
    val isCompleted: Boolean = false,
    val note: String = ""
)