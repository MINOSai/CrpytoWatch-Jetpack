package com.minosai.archusers.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.minosai.archusers.R
import com.minosai.archusers.network.WebService
import com.minosai.archusers.ui.fragment.InfoFragment
import com.minosai.archusers.ui.fragment.ListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),
        ListFragment.OnFragmentInteractionListener,
        InfoFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

//        val webService: WebService = WebService.create()
//        webService.fetchAllCryptos().enqueue(object : Callback<Any> {
//            override fun onFailure(call: Call<Any>?, t: Throwable?) {
//                Log.d("API_RESPONSE_FAILURE", "API RESPONSE FAILURE")
//            }
//
//            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
//                response?.let {
//                    if(response.isSuccessful) {
//                        Log.d("API_RESPONSE", response.body()?.toString())
////                        database.cryptoDao().insertCryptos(it.body()?.data!!)
//                    }
//                }
//            }
//
//        })

        val resId = resources.getIdentifier(
                "btc",
                "drawable",
                packageName
        )
        Log.i("RESID_CHECK", resId.toString())
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
        supportActionBar?.title = string
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.fragment_nav_host).navigateUp()

    fun getResId(resName: String, c: Class<*>): Int {
        try {
            val idField = c.getDeclaredField(resName)
            return idField.getInt(idField)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
    }
}
