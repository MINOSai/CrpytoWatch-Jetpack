package com.minosai.archusers.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.graphics.Color
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewGroup
import com.minosai.archusers.R
import com.minosai.archusers.model.CurrencyData
import com.minosai.archusers.model.User
import com.minosai.archusers.utils.inflate
import kotlinx.android.synthetic.main.item_crypto.view.*
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import org.jetbrains.anko.image
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.wrapContent


/**
 * Created by minos.ai on 16/05/18.
 */

class CurrencyAdapter(
        private val listener: (CurrencyData) -> Unit
) : PagedListAdapter<CurrencyData, CurrencyAdapter.UserViewHolder>(
        object : DiffUtil.ItemCallback<CurrencyData>() {
            override fun areItemsTheSame(oldItem: CurrencyData?, newItem: CurrencyData?) = oldItem?.id == newItem?.id
            override fun areContentsTheSame(oldItem: CurrencyData?, newItem: CurrencyData?) = oldItem == newItem
        }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent.inflate(R.layout.item_crypto))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) { getItem(position)?.let { holder.bind(it, listener) } }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val fcsRed = ForegroundColorSpan(Color.RED)
        val fcsGreen = ForegroundColorSpan(Color.GREEN)
        val bss = StyleSpan(android.graphics.Typeface.BOLD)

        fun bind(data: CurrencyData, listener: (CurrencyData) -> Unit) = with(itemView) {
            image_currency_logo.imageResource = getResId(this.context, data.symbol.toLowerCase())
            text_currency_name.text = getCurrencyName(data.symbol, data.name)
            text_currency_value.text = data.quotes.usd.price.toString()
            text_currency_1h.text = getCurrencyChange("1h", data.quotes.usd.change1Hour)
            text_currency_24h.text = getCurrencyChange("24h", data.quotes.usd.change24Hours)
            text_currency_7d.text = getCurrencyChange("7d", data.quotes.usd.change7Days)
            card_currency_item.setOnClickListener { listener(data) }
        }

        fun getResId(context: Context, resName: String): Int {
            try {
                return context.resources.getIdentifier(
                        resName,
                        "drawable",
                        context.packageName
                )
            } catch (e: Exception) {
                return R.drawable.btc
            }
        }

        fun getCurrencyName(symbol: String, name: String): SpannableStringBuilder {
            val text = "$symbol | $name"
            val sb = SpannableStringBuilder(text)
            sb.setSpan(
                    bss,
                    0,
                    text.indexOf("|") - 2,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            return sb
        }

        fun getCurrencyChange(prefix: String, value: Double): SpannableStringBuilder {
            val text = "$prefix: $value"
            val sb = SpannableStringBuilder(text)
            sb.setSpan(
                    if(value > 0) fcsGreen else fcsRed,
                    text.indexOf(" ") + 1,
                    text.lastIndex,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            return sb
        }
    }
}