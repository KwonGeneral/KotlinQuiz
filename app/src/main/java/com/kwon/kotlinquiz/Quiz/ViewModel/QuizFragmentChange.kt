package com.kwon.kotlinquiz.Quiz.ViewModel

import androidx.lifecycle.MutableLiveData
import com.kwon.kotlinquiz.Contain.Define.Companion.QUESTION

class QuizFragmentChange {
    var fragment_screen_tag = MutableLiveData(QUESTION)

    companion object {
        var instance: QuizFragmentChange? = null

        @JvmName("fragment_getInstance")
        fun getInstance(): QuizFragmentChange {
            instance?.let {
                return it
            }
            instance = QuizFragmentChange()
            return instance!!
        }
    }
}