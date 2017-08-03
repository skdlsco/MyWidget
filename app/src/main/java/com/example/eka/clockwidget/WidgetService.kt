package com.example.eka.clockwidget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by eka on 2017. 8. 3..
 */
class WidgetService : Service() {
    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("asdf", "onStartCommand")
        val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(this)

        val timer: Timer = Timer()
        timer.scheduleAtFixedRate(SetTime(this, appWidgetManager), 1, 1000)
        return START_REDELIVER_INTENT
    }

    class SetTime(context: Context, val appWidgetManager: AppWidgetManager) : TimerTask() {
        val remoteViews: RemoteViews = RemoteViews(context.packageName, R.layout.clock_widget)
        val widget: ComponentName = ComponentName(context, ClockWidget::class.java)
        override fun run() {
            remoteViews.setTextViewText(R.id.appwidget_text, getTime())
            appWidgetManager.updateAppWidget(widget, remoteViews)
        }

        fun getTime(): String {

            val dateFormat: SimpleDateFormat = SimpleDateFormat("HH : mm : ss", Locale.US)
            val time: String = dateFormat.format(Date())
            return time
        }
    }
}