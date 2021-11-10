package com.kwon.kotlinquiz.Quiz.Adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.kwon.kotlinquiz.Quiz.Model.QuizData

class QuizDiff: DiffUtil.ItemCallback<QuizData>() {
    // 아이템 비교 후 항목이 같을 경우
    override fun areItemsTheSame(oldItem: QuizData, newItem: QuizData): Boolean {
        Log.d("TEST", "항목이 같습니다")
        return oldItem.hashCode() == newItem.hashCode()
    }

    // 아이템 비교 후 내용이 같을 경우
    override fun areContentsTheSame(oldItem: QuizData, newItem: QuizData): Boolean {
        Log.d("TEST", "내용이 같습니다")
        return oldItem == newItem
    }
}