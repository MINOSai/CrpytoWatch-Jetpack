package com.minosai.archusers.utils

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat

import com.minosai.archusers.R
import android.app.NotificationChannel
import com.minosai.archusers.ui.MainActivity


/**
 * Helper class for showing and canceling refresh
 * notifications.
 *
 *
 * This class makes heavy use of the [NotificationCompat.Builder] helper
 * class to create notifications in a backward-compatible way.
 */
object NotificationHelper {

    val REFRESH_CHANNEL_ID = "com.minosai.archusres.channel.REFRESH_CHANNEL_ID"

    /**
     * The unique identifier for this type of notification.
     */
    private val NOTIFICATION_TAG = "Refresh"

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     *
     *
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     *
     *
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of refresh notifications. Make
     * sure to follow the
     * [
 * Notification design guidelines](https://developer.android.com/design/patterns/notifications.html) when doing so.
     *
     * @see .cancel
     */
    fun notifyRefresh(context: Context) {
        val res = context.resources

        val builder = NotificationCompat.Builder(context)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_stat_refresh)
                .setContentTitle("Cryptocurrencies refreshed")
                .setContentText("Time to check the prices of cryptocyrrencies")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(REFRESH_CHANNEL_ID)
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                Intent(context, MainActivity::class.java),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.ic_action_stat_share,
                        "Open",
                        PendingIntent.getActivity(
                                context,
                                0,
                                Intent(context, MainActivity::class.java),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)

        notify(context, builder.build())
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private fun notify(context: Context, notification: Notification) {
        val nm = context
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Refresh"
            val description = "Notifies when the currency data is refreshed"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(REFRESH_CHANNEL_ID, name, importance)
            channel.description = description
            nm.createNotificationChannel(channel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification)
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification)
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * [.notify].
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    fun cancel(context: Context) {
        val nm = context
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0)
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode())
        }
    }
}
