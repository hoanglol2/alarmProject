package com.example.alarm.utils

import android.text.format.DateFormat

object FormatTime {
    fun convertDate(timeInMillis: Long): String =
        DateFormat.format("HH:mm", timeInMillis).toString()
}