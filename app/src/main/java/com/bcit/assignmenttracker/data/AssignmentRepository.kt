package com.bcit.assignmenttracker.data

class AssignmentRepository(private val assignmentDao: AssignmentDao) {

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

    suspend fun getTodayAssignments(today: String): List<Assignment> {
        return assignmentDao.getTodayAssignments(today)
    }

    suspend fun getAssignmentByCourse(courseName: String): List<Assignment> {
        return assignmentDao.getAssignmentByCourse(courseName)
    }

}