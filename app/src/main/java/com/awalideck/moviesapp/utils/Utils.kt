package com.awalideck.moviesapp.utils

import android.net.Uri
import java.text.SimpleDateFormat
import java.util.*

/**
 * Changes the Date string format received from the API
 * e.g. 2021-06-23 is formatted to Jun 23, 2021
 */
fun formatDate(date: String): String {
    val newPattern = "MMM d, yyyy"
    val oldPattern = "yyyy-MM-dd"
    val oldDateFormat = SimpleDateFormat(oldPattern, Locale.getDefault())
    val oldDate = oldDateFormat.parse(date)!!
    val newDateFormat = SimpleDateFormat(newPattern, Locale.getDefault())
    return newDateFormat.format(oldDate)
}

fun getPosterURL(path: String): Uri {
    val uriBuilder = Uri.Builder()
    uriBuilder.apply {
        scheme("https")
            .authority("image.tmdb.org")
            .appendPath("t")
            .appendPath("p")
            .appendPath("original")
            .appendPath(path)
    }
    return uriBuilder.build()
}