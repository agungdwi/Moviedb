package com.example.moviedb

import android.content.Context

object Utils {

    fun calculateSpanCount(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        val desiredItemWidthDp = 120// Desired width of each grid item in dp
        return (screenWidthDp / desiredItemWidthDp).toInt()
    }
}