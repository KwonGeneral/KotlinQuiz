package com.kwon.kotlinquiz.Quiz.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.kwon.kotlinquiz.Quiz.Model.QuizData
import com.kwon.kotlinquiz.Quiz.Model.QuizDataBase

class QuizDataViewModel constructor(private val context: Context) {
    var quizReadData = MutableLiveData<List<QuizData>>()

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: QuizDataViewModel? = null

        @JvmName("fragment_getInstance")
        fun getInstance(context: Context): QuizDataViewModel {
            instance?.let {
                return it
            }
            instance = QuizDataViewModel(context)
            return instance!!
        }
    }

    fun onQuizCreate(question:String, answer:String) {
        QuizDataBase.getInstance(context)?.quizService()?.apply {
            quizCreate(
                QuizData(
                    id = null,
                    question = question,
                    answer = answer
                )
            )
            quizReadData.postValue(quizReadAll())
            Log.d("TEST", "생성~!! : ${quizReadData.value}")
        }
    }

    fun onQuizRead() {
        QuizDataBase.getInstance(context)?.quizService()?.quizReadAll()?.let {
            quizReadData.postValue(it)
            Log.d("TEST", "읽기~!! : $it")
        }
    }

    fun onQuizUpdate() {

    }

    fun onQuizDelete(id: Long) {
        QuizDataBase.getInstance(context)?.quizService()?.quizDelete(id)
        quizReadData.value = QuizDataBase.getInstance(context)?.quizService()?.quizReadAll()
        Log.d("TEST", "삭제~!! : ${quizReadData.value}")
    }

    fun onQuizSearch() {

    }
}