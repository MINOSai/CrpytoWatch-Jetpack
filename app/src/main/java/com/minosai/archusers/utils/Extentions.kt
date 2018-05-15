package com.minosai.archusers.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by minos.ai on 16/05/18.
 */

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}