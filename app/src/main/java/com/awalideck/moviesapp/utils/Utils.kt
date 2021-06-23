package com.awalideck.moviesapp.utils

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