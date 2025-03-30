package com.bcit.assignmenttracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AssignmentDao {
    @Query("SELECT * FROM assignment WHERE isCompleted = 0 ORDER BY deadline ASC")
    suspend fun getActiveAssignments(): List<Assignment>

    @Query("SELECT * FROM assignment WHERE isCompleted = 1 ORDER BY deadline ASC")
    suspend fun getCompletedAssignments(): List<Assignment>

    @Query("SELECT * FROM assignment ORDER BY deadline ASC")
    suspend fun getAllAssignments(): List<Assignment>

    @Query("SELECT * FROM assignment WHERE deadline IN (:dates) AND isCompleted = 0 ORDER BY deadline ASC")
    suspend fun getUpcomingAssignments(dates: List<String>): List<Assignment>

    @Insert
    suspend fun addAssignment(assignment: Assignment)

    @Delete
    suspend fun deleteAssignment(assignment: Assignment)

    @Update
    suspend fun updateAssignment(assignment: Assignment)
}
