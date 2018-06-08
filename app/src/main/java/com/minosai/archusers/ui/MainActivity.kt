package com.minosai.archusers.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.work.*
import com.minosai.archusers.R
import com.minosai.archusers.ui.fragment.InfoFragment
import com.minosai.archusers.ui.fragment.ListFragment
import com.minosai.archusers.utils.AlarmReceiver
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import com.minosai.archusers.utils.RefreshWorker
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(),
        ListFragment.OnFragmentInteractionListener,
        InfoFragment.OnFragmentInteractionListener,
        HasSupportFragmentInjector {

    val REFRESH_WORKER_TAG = "com.minosai.archusers.worker.REFRESH_WORKER"

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Cryptos"

//        startWorker()
        startAlarmManager()

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    private fun startAlarmManager() {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val currentTimeInMilli: Long = SystemClock.elapsedRealtime()
        val ONE_DAY = 24 * 60 * 60 * 1000
        val alarmTime = currentTimeInMilli + ONE_DAY
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, alarmTime, pendingIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onFragmentInteraction(string: String) {
//        supportActionBar?.title = string
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.fragment_nav_host).navigateUp()

    fun startWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val refreshWork = PeriodicWorkRequestBuilder<RefreshWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance().enqueue(refreshWork)
    }
}
