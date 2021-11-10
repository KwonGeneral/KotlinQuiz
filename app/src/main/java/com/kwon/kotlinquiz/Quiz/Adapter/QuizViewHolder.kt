package com.kwon.kotlinquiz.Quiz.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kwon.kotlinquiz.Quiz.Model.QuizData
import kotlinx.android.synthetic.main.quiz_viewholder.view.*

class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: QuizData) {
        with(itemView) {
            question_text.text = data.question
            answer_text.text = data.answer

            quiz_viewholder_layout.setOnClickListener {
                Snackbar.make(it, "아이템 $layoutPosition 터치!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun setAlpha(alpha: Float) {
        with(itemView) {
            question_text.alpha = alpha
            answer_text.alpha = alpha
        }
    }

}