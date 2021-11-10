package com.kwon.kotlinquiz.Quiz

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.kwon.kotlinquiz.Contain.Define.Companion.QUESTION
import com.kwon.kotlinquiz.Quiz.ViewModel.QuizFragmentChange
import com.kwon.kotlinquiz.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class QuizActivity : AppCompatActivity() {
    var firebaseDB = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // QuizActivity의 Fragment 전환
        QuizFragmentChange.getInstance()?.fragment_screen_tag?.observe(this, {changeFragment(it)})

        // FCM 토큰 생성 & Firebase DB 저장
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val fcm_list = mutableListOf<String>()
            firebaseDB.collection("User")?.let { db ->
                db.get().addOnCompleteListener { read ->
                    if (read.isSuccessful) {
                        for (document in read.result!!) {
                            fcm_list.add(document.data["fcm_token"].toString())
                        }

                        task.result.toString()?.let { fcm ->
                            if(fcm in fcm_list) {
                                return@addOnCompleteListener
                            }
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))?.let { time ->
                                Build.MODEL?.let { model ->
                                    val user: MutableMap<String, Any> = HashMap()
                                    user["fcm_token"] = fcm
                                    user["phone_model"] = model
                                    user["created_at"] = time

                                    db.add(user)?.apply {
                                        addOnSuccessListener {
                                            Toast.makeText(baseContext, "FCM 토큰을 Firebase DB에 저장했습니다", Toast.LENGTH_SHORT).show()
                                        }
                                        addOnFailureListener {
                                            Toast.makeText(baseContext, "FCM 토큰을 Firebase DB 저장에 실패했습니다", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    // Fragment 전환
    fun changeFragment(fragment_type:String) {
        supportFragmentManager?.beginTransaction()?.let { ft ->
            fragment_type?.let { ty ->
                when (ty) {
                    QUESTION -> QuestionFragment()?.apply { ft.replace(R.id.main_frag, this).commit() }
                    else -> QuestionFragment()?.apply { ft.replace(R.id.main_frag, this).commit() }
                }
            }
        }
    }
}