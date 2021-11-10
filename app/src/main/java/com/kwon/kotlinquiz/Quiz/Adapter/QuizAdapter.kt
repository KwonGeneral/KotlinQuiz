package com.kwon.kotlinquiz.Quiz.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kwon.kotlinquiz.Quiz.Model.QuizData
import com.kwon.kotlinquiz.Quiz.ViewModel.QuizDataViewModel
import com.kwon.kotlinquiz.R
import java.util.*

class QuizAdapter constructor(var context: Context): ListAdapter<QuizData, RecyclerView.ViewHolder>(QuizDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuizViewHolder(LayoutInflater.from(context).inflate(R.layout.quiz_viewholder, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is QuizViewHolder) {
            holder.bind(getItem(position) as QuizData)
        }
    }

    // 아이템 이동
    fun moveItem(fromPosition: Int, toPosition: Int) {
        currentList.toMutableList()?.let {
            Collections.swap(it, fromPosition, toPosition)
            submitList(it)
        }
    }

    // 아이템 삭제 및 DB 삭제
    fun removeItem(position: Int) {
        currentList.toMutableList()?.let {
            QuizDataViewModel.getInstance(context)?.onQuizDelete(it[position]?.id!!.toLong())
            it.removeAt(position)
            submitList(it)
        }
    }
}