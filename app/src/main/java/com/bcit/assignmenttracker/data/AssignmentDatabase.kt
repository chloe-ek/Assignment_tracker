package com.bcit.assignmenttracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Assignment::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assignmentDao(): AssignmentDao
}

// singleton pattern
object MyDatabase{
    fun getDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,"my_db")
            .allowMainThreadQueries()
            .build()
    }
}
