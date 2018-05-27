package com.minosai.archusers.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.minosai.archusers.R
import com.minosai.archusers.di.CryptoApp
import com.minosai.archusers.ui.viewmodel.CryptoViewModel
import com.minosai.archusers.ui.viewmodel.ViewModelFactory
import com.minosai.archusers.utils.setChangeText
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColor

class InfoFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private val cryptoViewModel: CryptoViewModel by lazy {
        ViewModelProviders.of(activity!!).get(CryptoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        listener?.onFragmentInteraction("User info")
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cryptoData = async {
            cryptoViewModel.getCryptoById(arguments?.getInt("cryptoid", 0)!!)
        }
        launch(UI) {
            val currencyData = cryptoData.await()
            activity?.let {
                currencyData.observe(it, Observer { data ->
                    img_info_coin_logo.imageResource = getResId(data!!.symbol.toLowerCase())
                    text_info_coin_symbol.text = data.symbol
                    text_info_coin_name.text = data.name
                    text_info_change_1h.setChangeText(data.quotes.usd.change1Hour)
                    text_info_change_24h.setChangeText(data.quotes.usd.change24Hours)
                    text_info_change_7d.setChangeText(data.quotes.usd.change7Days)
                })
            }
        }
    }

    fun getResId(resName: String): Int {
        try {
            return context!!.resources.getIdentifier(
                    resName,
                    "drawable",
                    context!!.packageName
            )
        } catch (e: Exception) {
            return R.drawable.btc
        }
    }

    fun onButtonPressed(string: String) {
        listener?.onFragmentInteraction(string)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(string: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()
    }
}
