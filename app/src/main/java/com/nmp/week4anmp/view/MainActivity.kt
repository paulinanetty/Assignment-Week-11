package com.nmp.week4anmp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.nmp.week4anmp.R
import com.nmp.week4anmp.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.nmp.week4anmp.util.createNotificationChannel


class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null

        fun showNotification(title: String, content: String, icon: Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder =
                NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }

            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext)

            if (ActivityCompat.checkSelfPermission(
                    instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1
                )

                return
            }
            notificationManager.notify(1001, notificationBuilder.build())

        }

    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("permission", "granted")
                createNotificationChannel(
                    this,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                    getString(R.string.app_name), "App notification channel."
                )

            } else {
                Log.d("permission", "deny")
            }

        }
    }


}


