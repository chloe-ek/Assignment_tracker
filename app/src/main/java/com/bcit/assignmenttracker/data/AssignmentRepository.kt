package com.bcit.assignmenttracker.data

import com.bcit.assignmenttracker.data.local.Assignment
import com.bcit.assignmenttracker.data.local.AssignmentDao
import com.bcit.assignmenttracker.data.remote.Motivation
import com.bcit.assignmenttracker.data.remote.MotivationRemoteSource

class AssignmentRepository(
    private val assignmentDao: AssignmentDao,
    private val motivationSource: MotivationRemoteSource
) {
    suspend fun getMotivation(): Motivation {
        return motivationSource.getMotivation()
    }

    suspend fun getActiveAssignments(): List<Assignment> {
        return assignmentDao.getActiveAssignments()
    }

    suspend fun getCompletedAssignments(): List<Assignment> {
        return assignmentDao.getCompletedAssignments()
    }

    suspend fun getAllAssignments(): List<Assignment> {
        return assignmentDao.getAllAssignments()
    }

    suspend fun addAssignment(assignment: Assignment){
        assignmentDao.addAssignment(assignment)
    }

    suspend fun deleteAssignment(assignment: Assignment){
        assignmentDao.deleteAssignment(assignment)
    }

    suspend fun updateAssignment(assignment: Assignment) {
        assignmentDao.updateAssignment(assignment)
    }

    suspend fun getUpcomingAssignments(dates: List<String>): List<Assignment> {
        return assignmentDao.getUpcomingAssignments(dates)
    }

}