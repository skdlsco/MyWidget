package com.example.eka.clockwidget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class ClockWidget : AppWidgetProvider() {
    var pendingIntent: PendingIntent? = null
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent: Intent = Intent(context, WidgetService::class.java)
        pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val TIME: Calendar = Calendar.getInstance();
        TIME.set(Calendar.MINUTE, 0)
        TIME.set(Calendar.SECOND, 0)
        TIME.set(Calendar.MILLISECOND, 0)
        alarmManager.setRepeating(AlarmManager.RTC, TIME.time.time, 60 * 1000, pendingIntent)
        Log.e("asdf", "asdf")

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    companion object {

    }
}

