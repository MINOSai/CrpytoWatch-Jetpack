package com.minosai.archusers.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.minosai.archusers.utils.NotificationHelper

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        NotificationHelper.notifyRefresh(context)
    }
}
