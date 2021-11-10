package com.kwon.kotlinquiz.Quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.kwon.kotlinquiz.Quiz.Adapter.QuizAdapter
import com.kwon.kotlinquiz.Quiz.Adapter.QuizTouchHelper
import com.kwon.kotlinquiz.Quiz.Model.QuizData
import com.kwon.kotlinquiz.Quiz.Model.QuizDataBase
import com.kwon.kotlinquiz.Quiz.ViewModel.QuizDataViewModel
import com.kwon.kotlinquiz.R
import kotlinx.android.synthetic.main.fragment_question.*


class QuestionFragment : Fragment() {

    companion object {
        fun newInstance(): QuestionFragment {
            return QuestionFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    private val quizada: QuizAdapter by lazy {
        QuizAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        QuizDataViewModel.getInstance(requireContext())?.let {
            it.quizReadData.observe(viewLifecycleOwner, { data ->
                with(question_recycler) {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//                    addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))  --> 구분선
                    adapter = quizada
                }
                quizada.submitList(data)
            })
        }

        QuizDataViewModel.getInstance(requireContext())?.onQuizRead()

        ItemTouchHelper(QuizTouchHelper(question_recycler)).attachToRecyclerView(question_recycler)

        var count = 1
        create_btn.setOnClickListener {
            QuizDataViewModel.getInstance(requireContext())?.onQuizCreate("테스트 $count", "테스트 $count")
            count++
        }

        shuffle_btn.setOnClickListener {
            quizada.submitList(QuizDataViewModel.getInstance(requireContext())?.quizReadData?.value?.shuffled())
        }
    }
}