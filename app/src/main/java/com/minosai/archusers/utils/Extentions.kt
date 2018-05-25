package com.minosai.archusers.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.textColor

/**
 * Created by minos.ai on 16/05/18.
 */

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun TextView.setChangeText(value: Double) {
    this.text = value.toString()
    this.textColor = if(value > 0) Color.GREEN else Color.RED
}