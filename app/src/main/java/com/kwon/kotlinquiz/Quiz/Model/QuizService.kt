package com.kwon.kotlinquiz.Quiz.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizService {
    @Query("SELECT * FROM tb_quiz")
    fun quizReadAll(): List<QuizData>

    @Insert
    fun quizCreate(vararg quiz: QuizData)

    @Query("SELECT * FROM tb_quiz WHERE question = :question")
    fun quizSearch(question: String): List<QuizData>

    @Query("DELETE FROM tb_quiz WHERE id = :id")
    fun quizDelete(id: Long)

    @Query("DELETE FROM tb_quiz")
    fun quizReset()
}