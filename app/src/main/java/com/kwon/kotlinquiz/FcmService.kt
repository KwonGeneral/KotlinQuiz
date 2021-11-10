package com.kwon.kotlinquiz

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kwon.kotlinquiz.Contain.Define.Companion.CHANNEL
import com.kwon.kotlinquiz.Contain.Define.Companion.OCHANNEL
import com.kwon.kotlinquiz.Quiz.QuizActivity
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.*


class FcmService: FirebaseMessagingService() {
    @SuppressLint("WrongThread")
    override fun onMessageReceived(remoteMessage: RemoteMessage)
    {
        Log.d("TEST", "onMessageReceived onMessageReceived onMessageReceived")
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            sendNotification(it.title, it.body!!)
        }
    }

    override fun onNewToken(token: String)
    {
        super.onNewToken(token)
    }

    @SuppressLint("ObsoleteSdkInt", "UnspecifiedImmutableFlag")
    private fun sendNotification(title: String?, body: String)
    {
        Log.d("TEST", "sendNotification sendNotification sendNotification")
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val pendingIntent = PendingIntent.getActivity(this, 0,
            Intent(this, QuizActivity::class.java)?.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }, PendingIntent.FLAG_ONE_SHOT)
//        val pendingIntent = PendingIntent.getActivity(this, 0,
//            Intent(Intent.ACTION_VIEW)?.apply {
//                data = Uri.parse(click_data)
//            }, PendingIntent.FLAG_ONE_SHOT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL,
                OCHANNEL,
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, NotificationCompat.Builder(this, CHANNEL).apply {
            setSmallIcon(R.mipmap.ic_launcher_round)
            setContentTitle(title)
            setContentText(body)
            setAutoCancel(true)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            setContentIntent(pendingIntent)
        }.build())
    }
}
