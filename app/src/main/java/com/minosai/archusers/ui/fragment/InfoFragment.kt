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
import com.minosai.archusers.ui.viewmodel.CryptoViewModel
import com.minosai.archusers.ui.viewmodel.ViewModelFactory
import com.minosai.archusers.utils.setChangeText
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColor


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InfoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val cryptoViewModel: CryptoViewModel by lazy {
        ViewModelProviders.of(activity!!, ViewModelFactory(activity!!.application)).get(CryptoViewModel::class.java)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
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

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(string: String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//                InfoFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
        @JvmStatic
        fun newInstance() = InfoFragment()
    }
}
