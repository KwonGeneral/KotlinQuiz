package com.kwon.kotlinquiz.Quiz.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuizData::class], version = 2, exportSchema = false)
abstract class QuizDataBase : RoomDatabase() {
    abstract fun quizService(): QuizService

    companion object {
        var instance: QuizDataBase? = null

        @Synchronized
        fun getInstance(context: Context): QuizDataBase? {
            instance?.let {
                return it
            }
            instance = Room.databaseBuilder(
                context.applicationContext,
                QuizDataBase::class.java,
                "quiz_db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return instance
        }
    }
}