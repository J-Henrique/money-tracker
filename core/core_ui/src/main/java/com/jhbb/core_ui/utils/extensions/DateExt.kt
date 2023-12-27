package com.jhbb.core_ui.utils.extensions

import android.content.Context
import android.text.format.DateFormat
import java.util.Date

fun Date.toFormattedDate(context: Context): String {
    return DateFormat.getMediumDateFormat(context).format(this)
}