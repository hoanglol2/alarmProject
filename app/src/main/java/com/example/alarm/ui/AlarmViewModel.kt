package com.example.alarm.ui

import android.app.TimePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import java.util.*

class AlarmViewModel : ViewModel() {
    /*companion object {
        var ALARM_DELAY_MINUTE : Int = 5
    }*/

    fun setAlarm(context: Context, callback: (Long) -> Unit) {
        Calendar.getInstance().apply {

            TimePickerDialog(
                context,
                0,
                { _, hour, min ->
                    this.set(Calendar.HOUR_OF_DAY, hour)
                    this.set(Calendar.MINUTE, min)
                    this.set(Calendar.SECOND, 0)
                    this.set(Calendar.MILLISECOND, 0)
                    callback(this.timeInMillis)
                },
                this.get(Calendar.HOUR_OF_DAY),
                this.get(Calendar.MINUTE),
                false
            ).show()
        }
    }
    /*fun setTimeDelay(value : Int) {
        ALARM_DELAY_MINUTE = value
    }*/
}