package com.kwon.kotlinquiz.Quiz.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kwon.kotlinquiz.Contain.Define.Companion.EMPTY_STR

@Entity(tableName = "tb_quiz")
data class QuizData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    @ColumnInfo(name = "question")
    val question: String? = EMPTY_STR,

    @ColumnInfo(name = "answer")
    val answer: String? = EMPTY_STR,
)
