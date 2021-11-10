package com.kwon.kotlinquiz.Quiz.Adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class QuizTouchHelper(private val recycler: RecyclerView): ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
    // 드래그
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        (recyclerView.adapter as QuizAdapter).moveItem(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    // 왼쪽으로 밀기
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (recycler.adapter as QuizAdapter).removeItem(viewHolder.layoutPosition)
    }

    // 아이템이 터치되어서 선택된 상태
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when(actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.ACTION_STATE_SWIPE -> {
                (viewHolder as QuizViewHolder).setAlpha(0.5f)
            }
        }
    }

    // 아이템을 놓을 경우
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as QuizViewHolder).setAlpha(1.0f)
    }
}